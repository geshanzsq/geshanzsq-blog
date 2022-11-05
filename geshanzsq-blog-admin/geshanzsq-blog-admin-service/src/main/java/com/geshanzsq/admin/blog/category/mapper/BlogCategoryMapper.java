package com.geshanzsq.admin.blog.category.mapper;

import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博客分类
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Mapper
public interface BlogCategoryMapper extends BaseMapperPlus<BlogCategory> {

    /**
     * 获取最大排序
     */
    Integer selectMaxSort();
}
