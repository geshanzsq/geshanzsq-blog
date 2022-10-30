package com.geshanzsq.client.blog.like.service;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.like.dto.BlogArticleLikeAddDTO;
import com.geshanzsq.client.blog.like.vo.BlogArticleLikeCountVO;
import com.geshanzsq.client.common.blog.po.like.BlogArticleLike;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 文章点赞
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
public interface BlogArticleLikeService extends BaseService<BlogArticleLike> {

    /**
     * 获取点赞数量
     */
    List<BlogArticleLikeCountVO> getCountByArticleIds(List<Long> articleIds);

    /**
     * 新增
     */
    void save(BlogArticleLikeAddDTO addDTO);

    /**
     * 分页列表
     */
    PageVO<BlogArticlePageVO> pageList(PageDTO pageDTO);
}
