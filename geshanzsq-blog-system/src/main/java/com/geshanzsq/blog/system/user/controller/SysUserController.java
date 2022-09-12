package com.geshanzsq.blog.system.user.controller;

import com.geshanzsq.blog.common.core.web.response.ResponseResult;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.controller.BaseController;
import com.geshanzsq.blog.common.log.annotation.Log;
import com.geshanzsq.blog.common.log.enums.BusinessType;
import com.geshanzsq.blog.system.user.dto.SysUserAddDTO;
import com.geshanzsq.blog.system.user.dto.SysUserPageDTO;
import com.geshanzsq.blog.system.user.dto.SysUserResetPasswordDTO;
import com.geshanzsq.blog.system.user.dto.SysUserUpdateDTO;
import com.geshanzsq.blog.system.user.mapstruct.SysUserConverter;
import com.geshanzsq.blog.system.user.po.SysUser;
import com.geshanzsq.blog.system.user.service.SysUserService;
import com.geshanzsq.blog.system.user.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 系统用户
 *
 * @author geshanzsq
 * @date 2022/3/20
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/page")
    @ApiOperation(("分页列表"))
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<SysUserVO>> page(SysUserPageDTO pageDto) {
        PageVO<SysUserVO> pageVo = sysUserService.pageList(pageDto);
        return ResponseResult.success(pageVo);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<SysUserVO> getById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser != null) {
            sysUser.setPassword(null);
        }
        return ResponseResult.success(SysUserConverter.INSTANCE.convert(sysUser));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody SysUserAddDTO addDto) {
        sysUserService.add(addDto);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody SysUserUpdateDTO updateDto) {
        SysUser sysUser = SysUserConverter.INSTANCE.convert(updateDto);
        sysUserService.updateById(sysUser);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        sysUserService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

    @PutMapping("/resetPassword")
    @ApiOperation("重置密码")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "用户管理-重置密码", businessType = BusinessType.UPDATE)
    public ResponseResult resetPassword(@Valid @RequestBody SysUserResetPasswordDTO sysUserResetPasswordDto) {
        sysUserService.resetPassword(sysUserResetPasswordDto);
        return ResponseResult.success();
    }

}
