package com.geshanzsq.blog.admin.blog.category.mapper;

import com.geshanzsq.blog.admin.blog.category.dto.BlogCategoryPageDTO;
import com.geshanzsq.blog.admin.blog.category.po.BlogCategory;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
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
     * 分页
     */
    default PageVO<BlogCategory> selectPage(BlogCategoryPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<BlogCategory>()
                .likeIf(BlogCategory::getCategoryName, pageDTO.getCategoryName())
                .eqIf(BlogCategory::getStatus, pageDTO.getStatus())
                .orderByAsc(BlogCategory::getSort, BlogCategory::getId)
        );
    }

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
