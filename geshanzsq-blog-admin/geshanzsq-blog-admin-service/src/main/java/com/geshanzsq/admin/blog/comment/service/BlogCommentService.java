package com.geshanzsq.admin.blog.comment.service;

import com.geshanzsq.admin.blog.comment.dto.BlogCommentPageDTO;
import com.geshanzsq.admin.blog.comment.vo.BlogCommentVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/26
 */
public interface BlogCommentService extends BaseService<BlogComment> {

    /**
     * 分页列表
     */
    PageVO<BlogCommentVO> pageList(BlogCommentPageDTO pageDTO);

    /**
     * 获取评论详情
     */
    BlogCommentVO getCommentById(Long id);
}
