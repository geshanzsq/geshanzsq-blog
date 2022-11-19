package com.geshanzsq.admin.blog.picture.service;

import com.geshanzsq.admin.blog.picture.po.BlogPictureCategory;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
public interface BlogPictureCategoryService extends BaseService<BlogPictureCategory> {

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
