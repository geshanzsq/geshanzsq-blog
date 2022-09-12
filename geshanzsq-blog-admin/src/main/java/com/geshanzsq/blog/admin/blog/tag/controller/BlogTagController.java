package com.geshanzsq.blog.admin.blog.tag.controller;

import com.geshanzsq.blog.admin.blog.tag.dto.BlogTagAddDTO;
import com.geshanzsq.blog.admin.blog.tag.dto.BlogTagListDTO;
import com.geshanzsq.blog.admin.blog.tag.dto.BlogTagPageDTO;
import com.geshanzsq.blog.admin.blog.tag.dto.BlogTagUpdateDTO;
import com.geshanzsq.blog.admin.blog.tag.mapstruct.BlogTagConverter;
import com.geshanzsq.blog.admin.blog.tag.po.BlogTag;
import com.geshanzsq.blog.admin.blog.tag.service.BlogTagService;
import com.geshanzsq.blog.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.blog.common.core.web.response.ResponseResult;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.log.annotation.Log;
import com.geshanzsq.blog.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 博客标签
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Api(tags = "博客标签")
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<BlogTagVO>> page(BlogTagPageDTO pageDTO) {
        PageVO<BlogTagVO> pageVo = blogTagService.pageList(pageDTO);
        return ResponseResult.success(pageVo);
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<List<BlogTagVO>> list(BlogTagListDTO listDTO) {
        List<BlogTag> list = blogTagService.list(listDTO);
        return ResponseResult.success(BlogTagConverter.INSTANCE.convertList(list));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogTagVO> getById(@PathVariable Long id) {
        return ResponseResult.success(BlogTagConverter.INSTANCE.convert(blogTagService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客标签", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody BlogTagAddDTO addDTO) {
        BlogTag blogTag = BlogTagConverter.INSTANCE.convert(addDTO);
        blogTagService.save(blogTag);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客标签", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody BlogTagUpdateDTO updateDTO) {
        BlogTag blogTag = BlogTagConverter.INSTANCE.convert(updateDTO);
        blogTagService.updateById(blogTag);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客标签", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogTagService.removeByIds(ids);
        return ResponseResult.success();
    }

    @GetMapping("/getMaxSort")
    @ApiOperation("获取最大排序")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = blogTagService.getMaxSort();
        return ResponseResult.success(maxSort);
    }

}
