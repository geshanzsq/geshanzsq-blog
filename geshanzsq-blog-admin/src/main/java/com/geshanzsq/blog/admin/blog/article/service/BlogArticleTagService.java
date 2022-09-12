package com.geshanzsq.blog.admin.blog.article.service;

import com.geshanzsq.blog.admin.blog.article.po.BlogArticleTag;
import com.geshanzsq.blog.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 文章标签
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
public interface BlogArticleTagService extends BaseService<BlogArticleTag> {

    /**
     * 是否关联文章
     *
     * @param tagIds 标签 id 集合
     * @return
     */
    boolean isAssociateArticle(List<Long> tagIds);

    /**
     * 保存文章对应标签
     *
     * @param articleId 文章 id
     * @param tagIds 标签 id 集合
     */
    void save(Long articleId, List<Long> tagIds);

    /**
     * 更新文章对应标签
     *
     * @param articleId 文章 id
     * @param tagIds 标签 id 集合
     */
    void update(Long articleId, List<Long> tagIds);
}
