package com.geshanzsq.blog.admin.blog.category.service;

import com.geshanzsq.blog.admin.blog.category.dto.BlogCategoryListDTO;
import com.geshanzsq.blog.admin.blog.category.dto.BlogCategoryPageDTO;
import com.geshanzsq.blog.admin.blog.category.po.BlogCategory;
import com.geshanzsq.blog.admin.blog.category.vo.BlogCategoryVO;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 博客分类
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public interface BlogCategoryService extends BaseService<BlogCategory> {

    /**
     * 分页列表
     */
    PageVO<BlogCategoryVO> pageList(BlogCategoryPageDTO pageDTO);

    /**
     * 列表
     */
    List<BlogCategory> list(BlogCategoryListDTO listDTO);

    /**
     * 删除
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
