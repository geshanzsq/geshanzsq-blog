package com.geshanzsq.blog.admin.blog.picture.service.impl;

import com.geshanzsq.blog.admin.blog.picture.dto.BlogPictureCategoryListDTO;
import com.geshanzsq.blog.admin.blog.picture.dto.BlogPictureCategoryPageDTO;
import com.geshanzsq.blog.admin.blog.picture.mapper.BlogPictureCategoryMapper;
import com.geshanzsq.blog.admin.blog.picture.mapstruct.BlogPictureCategoryConverter;
import com.geshanzsq.blog.admin.blog.picture.po.BlogPictureCategory;
import com.geshanzsq.blog.admin.blog.picture.service.BlogPictureCategoryService;
import com.geshanzsq.blog.admin.blog.picture.vo.BlogPictureCategoryVO;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Service
public class BlogPictureCategoryServiceImpl extends BaseServiceImpl<BlogPictureCategoryMapper, BlogPictureCategory> implements BlogPictureCategoryService {

    @Autowired
    private BlogPictureCategoryMapper blogPictureCategoryMapper;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogPictureCategoryVO> pageList(BlogPictureCategoryPageDTO pageDTO) {
        PageVO<BlogPictureCategory> pageVO = blogPictureCategoryMapper.selectPage(pageDTO);
        return BlogPictureCategoryConverter.INSTANCE.convert(pageVO);
    }

    /**
     * 列表
     */
    @Override
    public List<BlogPictureCategory> list(BlogPictureCategoryListDTO listDTO) {
        LambdaQueryWrapperPlus<BlogPictureCategory> wrapper = new LambdaQueryWrapperPlus<>();
        wrapper.eqIf(BlogPictureCategory::getStatus, listDTO.getStatus());
        wrapper.orderByAsc(BlogPictureCategory::getSort);
        List<BlogPictureCategory> list = blogPictureCategoryMapper.selectList(wrapper);
        return list;
    }

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return blogPictureCategoryMapper.getMaxSort();
    }
}
