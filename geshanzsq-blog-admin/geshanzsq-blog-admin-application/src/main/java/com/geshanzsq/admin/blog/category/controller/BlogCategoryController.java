package com.geshanzsq.admin.blog.category.controller;

import com.geshanzsq.admin.blog.category.dto.BlogCategoryAddDTO;
import com.geshanzsq.admin.blog.category.dto.BlogCategoryListDTO;
import com.geshanzsq.admin.blog.category.dto.BlogCategoryPageDTO;
import com.geshanzsq.admin.blog.category.dto.BlogCategoryUpdateDTO;
import com.geshanzsq.admin.blog.category.mapstruct.BlogCategoryConverter;
import com.geshanzsq.admin.blog.category.service.BlogCategoryService;
import com.geshanzsq.admin.blog.category.vo.BlogCategoryVO;
import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.log.annotation.Log;
import com.geshanzsq.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 博客分类
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Api(tags = "博客分类")
@RestController
@RequestMapping("/blog/category")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<BlogCategoryVO>> page(BlogCategoryPageDTO pageDTO) {
        pageDTO.setOrderColumn("sort,id");
        PageVO<BlogCategory> pageVO = blogCategoryService.page(pageDTO);
        return ResponseResult.success(BlogCategoryConverter.INSTANCE.convert(pageVO));
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<List<BlogCategoryVO>> list(BlogCategoryListDTO listDTO) {
        listDTO.setOrderColumn("sort");
        List<BlogCategory> list = blogCategoryService.list(listDTO);
        return ResponseResult.success(BlogCategoryConverter.INSTANCE.convertList(list));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogCategoryVO> getById(@PathVariable Long id) {
        return ResponseResult.success(BlogCategoryConverter.INSTANCE.convert(blogCategoryService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客分类", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody BlogCategoryAddDTO addDTO) {
        BlogCategory blogCategory = BlogCategoryConverter.INSTANCE.convert(addDTO);
        blogCategoryService.save(blogCategory);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客分类", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody BlogCategoryUpdateDTO updateDTO) {
        BlogCategory blogCategory = BlogCategoryConverter.INSTANCE.convert(updateDTO);
        blogCategoryService.updateById(blogCategory);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客分类", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogCategoryService.removeByIds(ids);
        return ResponseResult.success();
    }

    @GetMapping("/getMaxSort")
    @ApiOperation("获取最大排序")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = blogCategoryService.getMaxSort();
        return ResponseResult.success(maxSort);
    }

}
