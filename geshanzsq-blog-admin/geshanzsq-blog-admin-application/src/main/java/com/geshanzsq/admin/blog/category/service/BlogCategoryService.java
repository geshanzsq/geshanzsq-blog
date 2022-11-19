package com.geshanzsq.admin.blog.category.service;

import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 博客分类
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public interface BlogCategoryService extends BaseService<BlogCategory> {

    /**
     * 删除
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
