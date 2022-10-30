package com.geshanzsq.client.blog.category.service;

import com.geshanzsq.client.blog.category.vo.BlogCategoryListVO;
import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 分类
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
public interface BlogCategoryService extends BaseService<BlogCategory> {

    /**
     * 列表
     */
    List<BlogCategoryListVO> listCategory();

    /**
     * 更新点击量
     * @param categoryName 分类名称
     */
    void updateClickCountByCategoryName(String categoryName);
}
