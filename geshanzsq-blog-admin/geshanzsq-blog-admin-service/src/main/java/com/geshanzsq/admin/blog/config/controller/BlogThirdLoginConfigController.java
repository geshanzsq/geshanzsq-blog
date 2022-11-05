package com.geshanzsq.admin.blog.config.controller;

import com.geshanzsq.admin.blog.config.dto.BlogThirdLoginConfigAddDTO;
import com.geshanzsq.admin.blog.config.dto.BlogThirdLoginConfigPageDTO;
import com.geshanzsq.admin.blog.config.dto.BlogThirdLoginConfigUpdateDTO;
import com.geshanzsq.admin.blog.config.mapstruct.BlogThirdLoginConfigConverter;
import com.geshanzsq.admin.blog.config.service.BlogThirdLoginConfigService;
import com.geshanzsq.admin.blog.config.vo.BlogThirdLoginConfigVO;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
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
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Api(tags = "第三方登录配置")
@RestController
@RequestMapping("/blog/third/config")
public class BlogThirdLoginConfigController extends BaseController {

    @Autowired
    private BlogThirdLoginConfigService blogThirdLoginConfigService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<BlogThirdLoginConfigVO>> page(BlogThirdLoginConfigPageDTO pageDTO) {
        PageVO<BlogThirdLoginConfig> pageVO = blogThirdLoginConfigService.page(pageDTO);
        return ResponseResult.success(BlogThirdLoginConfigConverter.INSTANCE.convert(pageVO));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogThirdLoginConfigVO> getById(@PathVariable Long id) {
        return ResponseResult.success(BlogThirdLoginConfigConverter.INSTANCE.convert(blogThirdLoginConfigService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "第三方登录配置", businessType = BusinessType.ADD, isSaveRequestData = false)
    public ResponseResult add(@Valid @RequestBody BlogThirdLoginConfigAddDTO addDTO) {
        BlogThirdLoginConfig blogThirdLoginConfig = BlogThirdLoginConfigConverter.INSTANCE.convert(addDTO);
        blogThirdLoginConfigService.save(blogThirdLoginConfig);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "第三方登录配置", businessType = BusinessType.UPDATE, isSaveRequestData = false)
    public ResponseResult update(@Valid @RequestBody BlogThirdLoginConfigUpdateDTO updateDTO) {
        BlogThirdLoginConfig blogThirdLoginConfig = BlogThirdLoginConfigConverter.INSTANCE.convert(updateDTO);
        blogThirdLoginConfigService.updateById(blogThirdLoginConfig);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "第三方登录配置", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogThirdLoginConfigService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

}
