package com.geshanzsq.client.blog.article.mapper;

import com.geshanzsq.client.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.client.blog.article.dto.BlogArticleSearchPageDTO;
import com.geshanzsq.client.blog.article.vo.BlogArticleAboutVO;
import com.geshanzsq.client.blog.article.vo.BlogArticleCategoryCountVO;
import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.article.vo.BlogArticleRecommendVO;
import com.geshanzsq.client.common.blog.po.article.BlogArticle;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
@Mapper
public interface BlogArticleMapper extends BaseMapperPlus<BlogArticle> {

    /**
     * 分页列表
     */
    List<BlogArticlePageVO> selectPage(BlogArticlePageDTO pageDTO);

    /**
     * 搜索分页列表
     */
    List<BlogArticlePageVO> selectSearchPage(BlogArticleSearchPageDTO pageDTO);

    /**
     * 文章推荐
     * @param recommendLevel 推荐等级
     * @param status 文章状态
     * @param limitCount 查询条数
     * @return
     */
    List<BlogArticleRecommendVO> selectRecommend(@Param("recommendLevel") Integer recommendLevel,
                                                 @Param("status") Integer status,
                                                 @Param("limitCount") Integer limitCount);

    /**
     * 点击量排行
     * @param status 文章状态
     * @param limitCount 查询条数
     */
    List<BlogArticleRecommendVO> selectClickRanking(@Param("status") Integer status,
                                                    @Param("limitCount") Integer limitCount);

    /**
     * 获取文章数
     * @param status 文章状态
     */
    List<BlogArticleCategoryCountVO> selectArticleCategoryCount(@Param("status") Integer status);

    /**
     * 归档发布时间线
     * @param status 文章状态
     */
    List<String> selectArchiveGmtPublishLine(@Param("status") Integer status);

    /**
     * 更新文章阅读数
     *
     * @param id 文章 id
     */
    void updateArticleViewCount(@Param("id") Long id);

    /**
     * 获取相关文章
     *
     * @param id 文章 id
     * @param status 文章状态
     * @param limitCount 查询条数
     */
    List<BlogArticleAboutVO> selectAboutArticleById(@Param("id") Long id,
                                                    @Param("status") Integer status,
                                                    @Param("limitCount") int limitCount);
}
