package com.geshanzsq.admin.blog.picture.service;

import com.geshanzsq.admin.blog.picture.dto.BlogPicturePageDTO;
import com.geshanzsq.admin.blog.picture.po.BlogPicture;
import com.geshanzsq.admin.blog.picture.vo.BlogPictureUploadVO;
import com.geshanzsq.admin.blog.picture.vo.BlogPictureVO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;
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
