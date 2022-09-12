package com.geshanzsq.blog.admin.blog.picture.service;

import com.geshanzsq.blog.admin.blog.picture.dto.BlogPicturePageDTO;
import com.geshanzsq.blog.admin.blog.picture.po.BlogPicture;
import com.geshanzsq.blog.admin.blog.picture.vo.BlogPictureUploadVO;
import com.geshanzsq.blog.admin.blog.picture.vo.BlogPictureVO;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
public interface BlogPictureService extends BaseService<BlogPicture> {

    /**
     * 分页列表
     */
    PageVO<BlogPictureVO> pageList(BlogPicturePageDTO pageDTO);

    /**
     * 上传
     * @param file 文件
     * @param categoryId 所属分类
     */
    BlogPictureUploadVO upload(MultipartFile file, Long categoryId);
}
