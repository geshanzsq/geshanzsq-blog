package com.geshanzsq.admin.blog.comment.controller;

import com.geshanzsq.admin.blog.comment.dto.BlogCommentAuditDTO;
import com.geshanzsq.admin.blog.comment.dto.BlogCommentPageDTO;
import com.geshanzsq.admin.blog.comment.mapstruct.BlogCommentConverter;
import com.geshanzsq.admin.blog.comment.service.BlogCommentService;
import com.geshanzsq.admin.blog.comment.vo.BlogCommentVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.controller.BaseController;
import com.geshanzsq.common.log.annotation.Log;
import com.geshanzsq.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/26
 */
@Api(tags = "博客评论")
@RestController
@RequestMapping("/blog/comment")
public class BlogCommentController extends BaseController {

    @Autowired
    private BlogCommentService blogCommentService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<BlogCommentVO>> page(BlogCommentPageDTO pageDTO) {
        PageVO<BlogCommentVO> pageVO = blogCommentService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogCommentVO> getById(@PathVariable Long id) {
        BlogCommentVO blogCommentVO = blogCommentService.getCommentById(id);
        return ResponseResult.success(blogCommentVO);
    }


    @PutMapping("/audit")
    @ApiOperation("审核")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客评论", businessType = BusinessType.UPDATE)
    public ResponseResult audit(@Valid @RequestBody BlogCommentAuditDTO auditDTO) {
        BlogComment blogComment = BlogCommentConverter.INSTANCE.convert(auditDTO);
        blogCommentService.updateById(blogComment);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客评论", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogCommentService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

}
