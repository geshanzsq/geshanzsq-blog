package com.geshanzsq.admin.blog.picture.service.impl;

import com.geshanzsq.admin.blog.picture.mapper.BlogPictureCategoryMapper;
import com.geshanzsq.admin.blog.picture.po.BlogPictureCategory;
import com.geshanzsq.admin.blog.picture.service.BlogPictureCategoryService;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Service
public class BlogPictureCategoryServiceImpl extends BaseServiceImpl<BlogPictureCategoryMapper, BlogPictureCategory> implements BlogPictureCategoryService {

    @Autowired
    private BlogPictureCategoryMapper blogPictureCategoryMapper;

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return blogPictureCategoryMapper.selectMaxSort();
    }
}
