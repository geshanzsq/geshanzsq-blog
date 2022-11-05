package com.geshanzsq.admin.blog.picture.controller;

import com.geshanzsq.admin.blog.picture.dto.BlogPicturePageDTO;
import com.geshanzsq.admin.blog.picture.dto.BlogPictureUploadDTO;
import com.geshanzsq.admin.blog.picture.mapstruct.BlogPictureConverter;
import com.geshanzsq.admin.blog.picture.po.BlogPicture;
import com.geshanzsq.admin.blog.picture.service.BlogPictureService;
import com.geshanzsq.admin.blog.picture.vo.BlogPictureUploadVO;
import com.geshanzsq.admin.blog.picture.vo.BlogPictureVO;
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
 * 图片
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Api(tags = "博客图片")
@RestController
@RequestMapping("/blog/picture")
public class BlogPictureController extends BaseController {

    @Autowired
    private BlogPictureService blogPictureService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<BlogPictureVO>> page(BlogPicturePageDTO pageDTO) {
        pageDTO.setOrderColumn("gmtCreate");
        pageDTO.setOrderType("desc");
        PageVO<BlogPicture> pageVO = blogPictureService.page(pageDTO);
        return ResponseResult.success(BlogPictureConverter.INSTANCE.convert(pageVO));
    }

    @ApiOperation("上传")
    @PostMapping("/upload")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogPictureUploadVO> upload(@Valid BlogPictureUploadDTO uploadDTO) {
        BlogPictureUploadVO blogPictureUploadVO = blogPictureService.upload(uploadDTO.getFile(), uploadDTO.getCategoryId());
        return ResponseResult.success(blogPictureUploadVO);
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "博客图片", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogPictureService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

}
