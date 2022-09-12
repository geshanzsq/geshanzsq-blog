package com.geshanzsq.blog.admin.blog.picture.mapper;

import com.geshanzsq.blog.admin.blog.picture.dto.BlogPictureCategoryPageDTO;
import com.geshanzsq.blog.admin.blog.picture.po.BlogPictureCategory;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Mapper
public interface BlogPictureCategoryMapper extends BaseMapperPlus<BlogPictureCategory> {

    /**
     * 分页
     */
    default PageVO<BlogPictureCategory> selectPage(BlogPictureCategoryPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<BlogPictureCategory>()
                .likeIf(BlogPictureCategory::getCategoryName, pageDTO.getCategoryName())
                .eqIf(BlogPictureCategory::getStatus, pageDTO.getStatus())
                .orderByAsc(BlogPictureCategory::getSort, BlogPictureCategory::getId)
        );
    }

    /**
     * 获取最大排序
     */
    Integer getMaxSort();

    /**
     * 获取默认图片分类 id
     */
    Long selectDefaultCategoryId();
}
