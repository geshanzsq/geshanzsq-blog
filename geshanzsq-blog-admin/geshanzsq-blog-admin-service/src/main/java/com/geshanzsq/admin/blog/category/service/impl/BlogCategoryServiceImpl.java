package com.geshanzsq.admin.blog.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.admin.blog.article.mapper.BlogArticleMapper;
import com.geshanzsq.admin.blog.category.mapper.BlogCategoryMapper;
import com.geshanzsq.admin.blog.category.service.BlogCategoryService;
import com.geshanzsq.client.common.blog.po.article.BlogArticle;
import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 博客分类
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Service
public class BlogCategoryServiceImpl extends BaseServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private BlogArticleMapper blogArticleMapper;

    /**
     * 删除
     */
    @Override
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        // 判断分类是否已关联文章，如果已关联，则不能删除
        LambdaQueryWrapper<BlogArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BlogArticle::getCategoryId, ids);
        Long count = blogArticleMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ParamException(MessageUtils.message("blog.category.allocated.article"));
        }

        // 删除分类
        blogCategoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return blogCategoryMapper.selectMaxSort();
    }
}
