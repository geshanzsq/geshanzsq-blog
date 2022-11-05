package com.geshanzsq.admin.blog.picture.mapper;

import com.geshanzsq.admin.blog.picture.po.BlogPictureCategory;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
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
     * 获取最大排序
     */
    Integer selectMaxSort();

    /**
     * 获取默认图片分类 id
     */
    Long selectDefaultCategoryId();
}
