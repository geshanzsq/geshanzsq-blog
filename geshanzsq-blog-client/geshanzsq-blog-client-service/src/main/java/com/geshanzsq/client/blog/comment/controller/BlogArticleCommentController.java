package com.geshanzsq.client.blog.comment.controller;

import com.geshanzsq.client.blog.comment.service.BlogCommentService;
import com.geshanzsq.client.blog.comment.vo.BlogArticleCommentListVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章评论
 *
 * @author geshanzsq
 * @date 2022/10/23
 */
@Api(tags = "文章评论")
@RestController
@RequestMapping("/comment")
public class BlogArticleCommentController extends BaseController {

    @Autowired
    private BlogCommentService blogCommentService;


    @GetMapping("/listByArticleId")
    @ApiOperation("获取文章评论")
    public ResponseResult<List<BlogArticleCommentListVO>> listByArticleId(Long articleId) {
        List<BlogArticleCommentListVO> list = blogCommentService.listByArticleId(articleId);
        return ResponseResult.success(list);
    }

}
