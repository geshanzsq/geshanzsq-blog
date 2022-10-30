package com.geshanzsq.client.blog.like.mapper;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.like.vo.BlogArticleLikeCountVO;
import com.geshanzsq.client.common.blog.po.like.BlogArticleLike;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章点赞
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Mapper
public interface BlogArticleLikeMapper extends BaseMapperPlus<BlogArticleLike> {

    /**
     * 获取点赞数量
     */
    List<BlogArticleLikeCountVO> selectCountByArticleIds(@Param("articleIds") List<Long> articleIds);

    /**
     * 查询列表
     * @param articleStatus 文章状态
     * @param userId 用户 id
     * @return
     */
    List<BlogArticlePageVO> selectList(@Param("articleStatus") Integer articleStatus,
                                       @Param("userId") Long userId);
}
