package com.geshanzsq.admin.blog.article.service;

import com.geshanzsq.admin.blog.article.po.BlogArticleContent;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 文章内容
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
public interface BlogArticleContentService extends BaseService<BlogArticleContent> {

    /**
     * 保存文章内容
     *
     * @param articleId 文章 id
     * @param articleContent 文章内容
     */
    void save(Long articleId, String articleContent);

    /**
     * 更新文章内容
     *
     * @param articleId 文章 id
     * @param articleContent 文章内容
     */
    void update(Long articleId, String articleContent);
}
