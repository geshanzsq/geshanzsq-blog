package com.geshanzsq.client.blog.comment.service;

import com.geshanzsq.client.blog.comment.dto.BlogCommentSaveDTO;
import com.geshanzsq.client.blog.comment.vo.BlogArticleCommentListVO;
import com.geshanzsq.client.blog.comment.vo.BlogCommentPageVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
public interface BlogCommentService extends BaseService<BlogComment> {

    /**
     * 分页列表
     */
    PageVO<BlogCommentPageVO> pageList(PageDTO pageDTO);

    /**
     * 新增
     */
    void add(BlogCommentSaveDTO saveDTO);

    /**
     * 获取文章评论
     *
     * @param articleId 文章 id
     */
    List<BlogArticleCommentListVO> listByArticleId(Long articleId);
}
