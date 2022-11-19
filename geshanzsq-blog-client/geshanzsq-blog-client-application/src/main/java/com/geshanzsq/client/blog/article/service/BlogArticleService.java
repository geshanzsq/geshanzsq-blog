package com.geshanzsq.client.blog.article.service;

import com.geshanzsq.client.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.client.blog.article.dto.BlogArticleSearchPageDTO;
import com.geshanzsq.client.blog.article.vo.*;
import com.geshanzsq.client.common.blog.po.article.BlogArticle;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
public interface BlogArticleService extends BaseService<BlogArticle> {

    /**
     * 分页列表
     */
    PageVO<BlogArticlePageVO> pageList(BlogArticlePageDTO pageDTO);

    /**
     * 搜索分页列表
     */
    PageVO<BlogArticlePageVO> pageSearchList(BlogArticleSearchPageDTO pageDTO);

    /**
     * 文章推荐
     * @param recommendLevel 推荐等级
     */
    List<BlogArticleRecommendVO> getRecommend(Integer recommendLevel);

    /**
     * 点击量排行
     */
    List<BlogArticleRecommendVO> listClickRanking();

    /**
     * 获取文章分类数
     */
    List<BlogArticleCategoryCountVO> getArticleCategoryCount();

    /**
     * 归档发布时间线
     */
    List<String> getArchiveGmtPublishLine();

    /**
     * 获取文章详情
     */
    BlogArticleVO getArticleById(Long id);

    /**
     * 获取文章内容
     */
    BlogArticleContentVO getContentById(Long id);

    /**
     * 获取相关文章
     */
    List<BlogArticleAboutVO> getAboutArticleById(Long id);

    /**
     * 校验文章是否存在
     *
     * @param id 文章 id
     */
    void checkArticleExist(Long id);

    /**
     * 获取创建者用户 id
     *
     * @param id 文章 id
     * @return 创建者用户 id
     */
    Long getCreateUserId(Long id);
}
