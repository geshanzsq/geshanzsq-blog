package com.geshanzsq.admin.blog.article.service;

import com.geshanzsq.admin.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.admin.blog.article.dto.BlogArticleSaveDTO;
import com.geshanzsq.admin.blog.article.dto.BlogArticleUpdateDTO;
import com.geshanzsq.client.web.po.blog.article.BlogArticle;
import com.geshanzsq.admin.blog.article.vo.BlogArticleSaveVO;
import com.geshanzsq.admin.blog.article.vo.BlogArticleVO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/8/18
 */
public interface BlogArticleService extends BaseService<BlogArticle> {

    /**
     * 分页列表
     */
    PageVO<BlogArticleVO> pageList(BlogArticlePageDTO pageDTO);

    /**
     * 获取文章信息
     */
    BlogArticleVO getArticleById(Long id);

    /**
     * 保存文章
     * @return
     */
    BlogArticleSaveVO save(BlogArticleSaveDTO saveDTO);

    /**
     * 修改文章
     */
    void update(BlogArticleUpdateDTO updateDTO);

    /**
     * 删除
     */
    void removeByIds(Long[] ids);
}
