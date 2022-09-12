package com.geshanzsq.blog.admin.blog.picture.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.blog.admin.blog.picture.dto.BlogPicturePageDTO;
import com.geshanzsq.blog.admin.blog.picture.mapper.BlogPictureCategoryMapper;
import com.geshanzsq.blog.admin.blog.picture.mapper.BlogPictureMapper;
import com.geshanzsq.blog.admin.blog.picture.mapstruct.BlogPictureConverter;
import com.geshanzsq.blog.admin.blog.picture.po.BlogPicture;
import com.geshanzsq.blog.admin.blog.picture.service.BlogPictureService;
import com.geshanzsq.blog.admin.blog.picture.vo.BlogPictureUploadVO;
import com.geshanzsq.blog.admin.blog.picture.vo.BlogPictureVO;
import com.geshanzsq.blog.common.framework.file.property.FileUploadProperty;
import com.geshanzsq.blog.common.framework.file.service.FileService;
import com.geshanzsq.blog.common.framework.file.util.MimeTypeUtils;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 图片
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Service
public class BlogPictureServiceImpl extends BaseServiceImpl<BlogPictureMapper, BlogPicture> implements BlogPictureService {

    @Autowired
    private BlogPictureMapper blogPictureMapper;
    @Autowired
    private BlogPictureCategoryMapper blogPictureCategoryMapper;

    @Autowired
    private FileService fileService;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogPictureVO> pageList(BlogPicturePageDTO pageDTO) {
        PageVO<BlogPicture> pageVO = blogPictureMapper.selectPage(pageDTO);
        return BlogPictureConverter.INSTANCE.convert(pageVO);
    }

    /**
     * 上传
     * @param file 文件
     * @param categoryId 所属分类
     */
    @Override
    public BlogPictureUploadVO upload(MultipartFile file, Long categoryId) {
        if (file == null) {
            return null;
        }
        // 获取图片 md5
        String fileMd5 = fileService.getFileMd5(file);
        // 通过 md5 获取图片
        BlogPicture blogPicture = getByPictureMd5(fileMd5);
        // 已经上传过
        if (blogPicture != null) {
            return new BlogPictureUploadVO(file.getOriginalFilename(), blogPicture.getPicturePath());
        }

        // 图片没有上传过，上传文件
        String filePath = fileService.uploadImage(file);

        // 插入图片信息
        blogPicture = new BlogPicture();
        // 原始文件名称
        String originalFileName = file.getOriginalFilename();
        blogPicture.setPicturePath(filePath);
        blogPicture.setPictureOriginalName(originalFileName);
        blogPicture.setPictureMd5(fileMd5);
        blogPicture.setCategoryId(categoryId);
        // 所属图片为空，则获取默认分类
        if (categoryId == null) {
            Long defaultCategoryId = blogPictureCategoryMapper.selectDefaultCategoryId();
            blogPicture.setCategoryId(defaultCategoryId);
        }
        blogPictureMapper.insert(blogPicture);

        return new BlogPictureUploadVO(originalFileName, filePath);
    }

    /**
     * 通过 md5 获取图片
     */
    private BlogPicture getByPictureMd5(String pictureMd5) {
        LambdaQueryWrapper<BlogPicture> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogPicture::getPictureMd5, pictureMd5);
        List<BlogPicture> list = blogPictureMapper.selectList(wrapper);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
