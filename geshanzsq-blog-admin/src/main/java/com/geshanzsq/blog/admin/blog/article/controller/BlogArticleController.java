package com.geshanzsq.blog.admin.blog.article.controller;

import com.geshanzsq.blog.admin.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.blog.admin.blog.article.dto.BlogArticleSaveDTO;
import com.geshanzsq.blog.admin.blog.article.dto.BlogArticleUpdateDTO;
import com.geshanzsq.blog.admin.blog.article.service.BlogArticleService;
import com.geshanzsq.blog.admin.blog.article.vo.BlogArticleSaveVO;
import com.geshanzsq.blog.admin.blog.article.vo.BlogArticleVO;
import com.geshanzsq.blog.common.core.web.response.ResponseResult;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.controller.BaseController;
import com.geshanzsq.blog.common.log.annotation.Log;
import com.geshanzsq.blog.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/8/18
 */
@Api(tags = "文章")
@RestController
@RequestMapping("/blog/article")
public class BlogArticleController extends BaseController {

    @Autowired
    private BlogArticleService blogArticleService;

    @PostMapping("/page")
    @ApiOperation("分页列表")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<BlogArticleVO>> page(@RequestBody BlogArticlePageDTO pageDTO) {
        PageVO<BlogArticleVO> pageVO = blogArticleService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogArticleVO> getById(@PathVariable Long id) {
        BlogArticleVO blogArticleVO = blogArticleService.getArticleById(id);
        return ResponseResult.success(blogArticleVO);
    }

    @PostMapping()
    @ApiOperation("保存")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "文章", businessType = BusinessType.ADD)
    public ResponseResult<BlogArticleSaveVO> save(@Valid @RequestBody BlogArticleSaveDTO saveDTO) {
        BlogArticleSaveVO blogArticleSaveVO = blogArticleService.save(saveDTO);
        return ResponseResult.success(blogArticleSaveVO);
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "文章", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody BlogArticleUpdateDTO updateDTO) {
        blogArticleService.update(updateDTO);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "文章", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogArticleService.removeByIds(ids);
        return ResponseResult.success();
    }

}
