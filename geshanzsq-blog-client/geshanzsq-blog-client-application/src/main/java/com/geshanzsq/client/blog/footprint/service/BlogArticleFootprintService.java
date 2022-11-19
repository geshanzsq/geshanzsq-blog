package com.geshanzsq.client.blog.footprint.service;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.common.blog.po.footprint.BlogArticleFootprint;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 文章足迹
 *
 * @author geshanzsq
 * @date 2022/10/14
 */
public interface BlogArticleFootprintService extends BaseService<BlogArticleFootprint> {

    /**
     * 保存足迹
     *
     * @param userId 用户 id
     * @param articleId 文章 id
     */
    void save(Long userId, Long articleId);

    /**
     * 分页列表
     */
    PageVO<BlogArticlePageVO> pageList(PageDTO pageDTO);
}
