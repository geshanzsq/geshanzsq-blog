package com.geshanzsq.admin.blog.article.service.impl;

import com.geshanzsq.admin.blog.article.mapper.BlogArticleContentMapper;
import com.geshanzsq.admin.blog.article.service.BlogArticleContentService;
import com.geshanzsq.client.common.blog.po.article.BlogArticleContent;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文章内容
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Service
public class BlogArticleContentServiceImpl extends BaseServiceImpl<BlogArticleContentMapper, BlogArticleContent> implements BlogArticleContentService {

    @Autowired
    private BlogArticleContentMapper blogArticleContentMapper;

    /**
     * 保存文章内容
     *
     * @param articleId 文章 id
     * @param articleContent 文章内容
     */
    @Override
    public void save(Long articleId, String articleContent) {
        BlogArticleContent blogArticleContent = new BlogArticleContent();
        blogArticleContent.setArticleContent(articleContent);
        blogArticleContent.setId(articleId);
        blogArticleContentMapper.insert(blogArticleContent);
    }

    /**
     * 更新文章内容
     *
     * @param articleId 文章 id
     * @param articleContent 文章内容
     */
    @Override
    public void update(Long articleId, String articleContent) {
        BlogArticleContent blogArticleContent = blogArticleContentMapper.selectById(articleId);
        if (blogArticleContent != null) {
            // 更新文章内容，只有内容改变时才更新
            if (!articleContent.equals(blogArticleContent.getArticleContent())) {
                BlogArticleContent blogArticleContentUpdate = new BlogArticleContent();
                blogArticleContentUpdate.setId(blogArticleContent.getId());
                blogArticleContentUpdate.setId(articleId);
                blogArticleContentUpdate.setArticleContent(articleContent);
                blogArticleContentMapper.updateById(blogArticleContentUpdate);
            }
        } else {
            // 插入文章内容
            this.save(articleId, articleContent);
        }
    }
}
