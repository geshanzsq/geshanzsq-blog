package com.geshanzsq.client.blog.comment.controller;

import com.geshanzsq.client.blog.comment.dto.BlogCommentPageDTO;
import com.geshanzsq.client.blog.comment.dto.BlogCommentSaveDTO;
import com.geshanzsq.client.blog.comment.service.BlogCommentService;
import com.geshanzsq.client.blog.comment.vo.BlogCommentPageVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
@Api(tags = "文章用户评论")
@RestController
@RequestMapping("/user/comment")
public class BlogCommentController extends BaseController {

    @Autowired
    private BlogCommentService articleCommentService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    public ResponseResult<PageVO<BlogCommentPageVO>> page(BlogCommentPageDTO pageDTO) {
        PageVO<BlogCommentPageVO> pageVO = articleCommentService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("新增")
    @PostMapping()
    public ResponseResult add(@Valid @RequestBody BlogCommentSaveDTO saveDTO) {
        articleCommentService.add(saveDTO);
        return ResponseResult.success();
    }



}
