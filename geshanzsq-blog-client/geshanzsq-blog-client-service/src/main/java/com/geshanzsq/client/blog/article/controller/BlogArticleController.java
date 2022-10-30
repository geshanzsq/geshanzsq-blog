package com.geshanzsq.client.blog.article.controller;

import com.geshanzsq.client.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.client.blog.article.dto.BlogArticleSearchPageDTO;
import com.geshanzsq.client.blog.article.service.BlogArticleService;
import com.geshanzsq.client.blog.article.vo.*;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
@Api(tags = "文章")
@RestController
@RequestMapping("/article")
public class BlogArticleController extends BaseController {

    @Autowired
    private BlogArticleService blogArticleService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    public ResponseResult<PageVO<BlogArticlePageVO>> page(BlogArticlePageDTO pageDTO) {
        PageVO<BlogArticlePageVO> pageVO = blogArticleService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("搜索分页列表")
    @GetMapping("/pageSearch")
    public ResponseResult<PageVO<BlogArticlePageVO>> pageSearch(BlogArticleSearchPageDTO pageDTO) {
        PageVO<BlogArticlePageVO> pageVO = blogArticleService.pageSearchList(pageDTO);
        return ResponseResult.success(pageVO);
    }


    @ApiOperation("文章推荐")
    @GetMapping("/getRecommend")
    public ResponseResult<List<BlogArticleRecommendVO>> getRecommend(Integer recommendLevel) {
        List<BlogArticleRecommendVO> list = blogArticleService.getRecommend(recommendLevel);
        return ResponseResult.success(list);
    }

    @ApiOperation("文章点击量排行")
    @GetMapping("/listClickRanking")
    public ResponseResult<List<BlogArticleRecommendVO>> listClickRanking() {
        List<BlogArticleRecommendVO> list = blogArticleService.listClickRanking();
        return ResponseResult.success(list);
    }

    @ApiOperation("获取文章详情")
    @GetMapping("/getById/{id}")
    public ResponseResult<BlogArticleVO> getById(@PathVariable Long id) {
        BlogArticleVO blogArticleVO = blogArticleService.getArticleById(id);
        return ResponseResult.success(blogArticleVO);
    }

    @ApiOperation("获取文章内容")
    @GetMapping("/getContentById/{id}")
    public ResponseResult<BlogArticleContentVO> getContentById(@PathVariable Long id) {
        BlogArticleContentVO blogArticleContentVO = blogArticleService.getContentById(id);
        return ResponseResult.success(blogArticleContentVO);
    }

    @ApiOperation("获取相关文章")
    @GetMapping("/getAboutArticleById/{id}")
    public ResponseResult<List<BlogArticleAboutVO>> getAboutArticleById(@PathVariable Long id) {
        List<BlogArticleAboutVO> list = blogArticleService.getAboutArticleById(id);
        return ResponseResult.success(list);
    }

}
