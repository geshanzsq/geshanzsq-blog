package com.geshanzsq.blog.admin.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.blog.admin.blog.article.mapper.BlogArticleTagMapper;
import com.geshanzsq.blog.admin.blog.article.po.BlogArticleTag;
import com.geshanzsq.blog.admin.blog.article.service.BlogArticleTagService;
import com.geshanzsq.blog.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章标签
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Service
public class BlogArticleTagServiceImpl extends BaseServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements BlogArticleTagService {

    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    /**
     * 是否关联文章
     *
     * @param tagIds 标签 id 集合
     * @return
     */
    @Override
    public boolean isAssociateArticle(List<Long> tagIds) {
        if (CollectionUtils.isEmpty(tagIds)) {
            return false;
        }
        return blogArticleTagMapper.selectAssociateArticleCount(tagIds) > 0;
    }

    /**
     * 保存文章对应标签
     *
     * @param articleId 文章 id
     * @param tagIds 标签 id 集合
     */
    @Override
    public void save(Long articleId, List<Long> tagIds) {
        if (CollectionUtils.isEmpty(tagIds)) {
            return;
        }
        List<BlogArticleTag> tagList = new ArrayList<>();
        tagIds.forEach(tagId -> {
            BlogArticleTag blogArticleTag = new BlogArticleTag();
            blogArticleTag.setArticleId(articleId);
            blogArticleTag.setTagId(tagId);
            tagList.add(blogArticleTag);
        });
        super.saveBatch(tagList);
    }

    /**
     * 更新文章对应标签
     *
     * @param articleId 文章 id
     * @param tagIds 标签 id 集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long articleId, List<Long> tagIds) {
        // 1 删除文章对应标签
        LambdaQueryWrapper<BlogArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogArticleTag::getArticleId, articleId);
        blogArticleTagMapper.delete(wrapper);

        // 2 保存文章对应标签
        this.save(articleId, tagIds);
    }
}
