package com.geshanzsq.blog.admin.blog.picture.service;

import com.geshanzsq.blog.admin.blog.picture.dto.BlogPictureCategoryListDTO;
import com.geshanzsq.blog.admin.blog.picture.dto.BlogPictureCategoryPageDTO;
import com.geshanzsq.blog.admin.blog.picture.po.BlogPictureCategory;
import com.geshanzsq.blog.admin.blog.picture.vo.BlogPictureCategoryVO;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
public interface BlogPictureCategoryService extends BaseService<BlogPictureCategory> {

    /**
     * 分页列表
     */
    PageVO<BlogPictureCategoryVO> pageList(BlogPictureCategoryPageDTO pageDTO);

    /**
     * 列表
     */
    List<BlogPictureCategory> list(BlogPictureCategoryListDTO listDTO);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
