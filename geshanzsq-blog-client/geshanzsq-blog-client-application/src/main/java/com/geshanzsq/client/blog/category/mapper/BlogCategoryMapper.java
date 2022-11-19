package com.geshanzsq.client.blog.category.mapper;

import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 分类
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Mapper
public interface BlogCategoryMapper extends BaseMapperPlus<BlogCategory> {

    /**
     * 更新点击量
     * @param categoryName 分类名称
     * @param status 状态
     */
    void updateClickCountByCategoryName(@Param("categoryName") String categoryName,
                                        @Param("status") Integer status);
}
