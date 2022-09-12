package com.geshanzsq.blog.system.param.controller;

import com.geshanzsq.blog.common.core.web.response.ResponseResult;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.controller.BaseController;
import com.geshanzsq.blog.common.log.annotation.Log;
import com.geshanzsq.blog.common.log.enums.BusinessType;
import com.geshanzsq.blog.system.param.dto.SysParamAddDTO;
import com.geshanzsq.blog.system.param.dto.SysParamPageDTO;
import com.geshanzsq.blog.system.param.dto.SysParamUpdateDTO;
import com.geshanzsq.blog.system.param.mapstruct.SysParamConverter;
import com.geshanzsq.blog.system.param.po.SysParam;
import com.geshanzsq.blog.system.param.service.SysParamService;
import com.geshanzsq.blog.system.param.vo.SysParamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 参数配置
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Api(tags = "参数配置")
@RestController
@RequestMapping("/system/param")
public class SysParamController extends BaseController {

    @Autowired
    private SysParamService sysParamService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysParamVO>> page(@Valid SysParamPageDTO pageDTO) {
        PageVO<SysParamVO> pageVo = sysParamService.pageList(pageDTO);
        return ResponseResult.success(pageVo);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysParamVO> getById(@PathVariable Long id) {
        return ResponseResult.success(SysParamConverter.INSTANCE.convert(sysParamService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "参数配置", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody SysParamAddDTO addDTO) {
        SysParam sysParam = SysParamConverter.INSTANCE.convert(addDTO);
        sysParamService.save(sysParam);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "参数配置", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody SysParamUpdateDTO updateDTO) {
        SysParam sysParam = SysParamConverter.INSTANCE.convert(updateDTO);
        sysParamService.updateById(sysParam);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "参数配置", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysParamService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

    @GetMapping("/getMaxSort")
    @ApiOperation("获取最大排序")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = sysParamService.getMaxSort();
        return ResponseResult.success(maxSort);
    }

}
