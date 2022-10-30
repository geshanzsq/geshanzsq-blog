package com.geshanzsq.client.blog.collect.service;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.collect.dto.BlogArticleCollectAddDTO;
import com.geshanzsq.client.blog.collect.vo.BlogArticleCollectCountVO;
import com.geshanzsq.client.common.blog.po.collect.BlogArticleCollect;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 文章收藏
 *
 * @author geshanzsq
 * @date 2022/10/8
 */
public interface BlogArticleCollectService extends BaseService<BlogArticleCollect> {

    /**
     * 获取收藏数量
     */
    List<BlogArticleCollectCountVO> getCountByArticleIds(List<Long> articleIds);

    /**
     * 新增
     */
    void save(BlogArticleCollectAddDTO addDTO);

    /**
     * 分页列表
     */
    PageVO<BlogArticlePageVO> pageList(PageDTO pageDTO);
}
