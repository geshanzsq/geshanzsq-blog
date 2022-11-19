package com.geshanzsq.client.blog.footprint.mapper;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.common.blog.po.footprint.BlogArticleFootprint;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章足迹
 *
 * @author geshanzsq
 * @date 2022/10/14
 */
public interface BlogArticleFootprintMapper extends BaseMapperPlus<BlogArticleFootprint> {

    /**
     * 查询列表
     * @param articleStatus 文章状态
     * @param userId 用户 id
     * @return
     */
    List<BlogArticlePageVO> selectPage(@Param("articleStatus") Integer articleStatus,
                                       @Param("userId") Long userId);
}
