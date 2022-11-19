package com.geshanzsq.client.blog.collect.controller;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.collect.dto.BlogArticleCollectAddDTO;
import com.geshanzsq.client.blog.collect.service.BlogArticleCollectService;
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
 * 文章收藏
 *
 * @author geshanzsq
 * @date 2022/10/14
 */
@Api(tags = "文章收藏")
@RestController
@RequestMapping("/user/collect")
public class BlogArticleCollectController extends BaseController {

    @Autowired
    private BlogArticleCollectService blogArticleCollectService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    public ResponseResult<PageVO<BlogArticlePageVO>> page(PageDTO pageDTO) {
        PageVO<BlogArticlePageVO> pageVO = blogArticleCollectService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("新增")
    @PostMapping()
    public ResponseResult add(@Valid @RequestBody BlogArticleCollectAddDTO addDTO) {
        blogArticleCollectService.save(addDTO);
        return ResponseResult.success();
    }

}
