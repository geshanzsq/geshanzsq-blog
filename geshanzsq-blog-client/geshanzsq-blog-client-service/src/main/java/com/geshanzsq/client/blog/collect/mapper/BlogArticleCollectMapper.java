package com.geshanzsq.client.blog.collect.mapper;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.collect.vo.BlogArticleCollectCountVO;
import com.geshanzsq.client.common.blog.po.collect.BlogArticleCollect;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章收藏
 *
 * @author geshanzsq
 * @date 2022/10/8
 */
public interface BlogArticleCollectMapper extends BaseMapperPlus<BlogArticleCollect> {

    /**
     * 获取收藏数量
     */
    List<BlogArticleCollectCountVO> selectCountByArticleIds(@Param("articleIds") List<Long> articleIds);

    /**
     * 查询列表
     * @param articleStatus 文章状态
     * @param userId 用户 id
     * @return
     */
    List<BlogArticlePageVO> selectList(@Param("articleStatus") Integer articleStatus,
                                       @Param("userId") Long userId);
}
