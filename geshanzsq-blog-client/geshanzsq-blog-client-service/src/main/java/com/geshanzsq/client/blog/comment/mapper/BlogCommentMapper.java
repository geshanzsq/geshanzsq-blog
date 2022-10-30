package com.geshanzsq.client.blog.comment.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.comment.vo.BlogArticleCommentListVO;
import com.geshanzsq.client.blog.comment.vo.BlogCommentPageVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
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
     * 分页列表
     */
    default PageVO<BlogComment> selectPage(PageDTO pageDTO, Long userId) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<BlogComment>()
                .eq(BlogComment::getCreateUserId, userId)
                .orderByDesc(BlogComment::getGmtCreate)
        );
    }

    /**
     * 获取文章评论
     *
     * @param articleId 文章 id
     * @param status 评论状态
     */
    List<BlogArticleCommentListVO> selectListByArticleId(@Param("articleId") Long articleId,
                                                         @Param("status") Integer status);
}
