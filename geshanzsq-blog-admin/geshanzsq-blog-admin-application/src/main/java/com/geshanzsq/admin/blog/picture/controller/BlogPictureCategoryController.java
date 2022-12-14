package com.geshanzsq.admin.blog.picture.controller;

import com.geshanzsq.admin.blog.picture.dto.BlogPictureCategoryAddDTO;
import com.geshanzsq.admin.blog.picture.dto.BlogPictureCategoryListDTO;
import com.geshanzsq.admin.blog.picture.dto.BlogPictureCategoryPageDTO;
import com.geshanzsq.admin.blog.picture.dto.BlogPictureCategoryUpdateDTO;
import com.geshanzsq.admin.blog.picture.mapstruct.BlogPictureCategoryConverter;
import com.geshanzsq.admin.blog.picture.po.BlogPictureCategory;
import com.geshanzsq.admin.blog.picture.service.BlogPictureCategoryService;
import com.geshanzsq.admin.blog.picture.vo.BlogPictureCategoryVO;
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
import java.util.List;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Api(tags = "博客图片分类")
@RestController
@RequestMapping("/blog/picture/category")
public class BlogPictureCategoryController extends BaseController {

    @Autowired
    private BlogPictureCategoryService blogPictureCategoryService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<BlogPictureCategoryVO>> page(BlogPictureCategoryPageDTO pageDTO) {
        pageDTO.setOrderColumn("sort,id");
        PageVO<BlogPictureCategory> pageVO = blogPictureCategoryService.page(pageDTO);
        return ResponseResult.success(BlogPictureCategoryConverter.INSTANCE.convert(pageVO));
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<List<BlogPictureCategoryVO>> page(BlogPictureCategoryListDTO listDTO) {
        listDTO.setOrderColumn("sort");
        List<BlogPictureCategory> list = blogPictureCategoryService.list(listDTO);
        return ResponseResult.success(BlogPictureCategoryConverter.INSTANCE.convert(list));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogPictureCategoryVO> getById(@PathVariable Long id) {
        return ResponseResult.success(BlogPictureCategoryConverter.INSTANCE.convert(blogPictureCategoryService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客图片分类", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody BlogPictureCategoryAddDTO addDTO) {
        BlogPictureCategory blogPictureCategory = BlogPictureCategoryConverter.INSTANCE.convert(addDTO);
        blogPictureCategoryService.save(blogPictureCategory);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客图片分类", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody BlogPictureCategoryUpdateDTO updateDTO) {
        BlogPictureCategory blogPictureCategory = BlogPictureCategoryConverter.INSTANCE.convert(updateDTO);
        blogPictureCategoryService.updateById(blogPictureCategory);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客图片分类", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogPictureCategoryService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

    @GetMapping("/getMaxSort")
    @ApiOperation("获取最大排序")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = blogPictureCategoryService.getMaxSort();
        return ResponseResult.success(maxSort);
    }

}
