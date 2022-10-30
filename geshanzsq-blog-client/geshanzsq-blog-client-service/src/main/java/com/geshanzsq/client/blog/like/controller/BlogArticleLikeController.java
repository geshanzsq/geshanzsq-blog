package com.geshanzsq.client.blog.like.controller;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.like.dto.BlogArticleLikeAddDTO;
import com.geshanzsq.client.blog.like.service.BlogArticleLikeService;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 文章点赞
 *
 * @author geshanzsq
 * @date 2022/10/8
 */
@Api(tags = "文章点赞")
@RestController
@RequestMapping("/user/like")
public class BlogArticleLikeController extends BaseController {

    @Autowired
    private BlogArticleLikeService blogArticleLikeService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    public ResponseResult<PageVO<BlogArticlePageVO>> page(PageDTO pageDTO) {
        PageVO<BlogArticlePageVO> pageVO = blogArticleLikeService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("新增")
    @PostMapping()
    public ResponseResult add(@Valid @RequestBody BlogArticleLikeAddDTO addDTO) {
        blogArticleLikeService.save(addDTO);
        return ResponseResult.success();
    }

}
