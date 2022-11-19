package com.geshanzsq.client.blog.comment.mapper;

import com.geshanzsq.client.blog.comment.vo.BlogArticleCommentListVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
@Mapper
public interface BlogCommentMapper extends BaseMapperPlus<BlogComment> {

    /**
     * 获取文章评论
     *
     * @param articleId 文章 id
     * @param status 评论状态
     */
    List<BlogArticleCommentListVO> selectListByArticleId(@Param("articleId") Long articleId,
                                                         @Param("status") Integer status);
}
