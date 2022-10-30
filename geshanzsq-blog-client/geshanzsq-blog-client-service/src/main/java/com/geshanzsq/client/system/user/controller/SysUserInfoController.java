package com.geshanzsq.client.system.user.controller;

import com.geshanzsq.admin.system.common.po.user.SysUser;
import com.geshanzsq.client.system.user.dto.SysUserUpdateDTO;
import com.geshanzsq.client.system.user.mapstruct.SysUserConverter;
import com.geshanzsq.client.system.user.service.SysUserInfoService;
import com.geshanzsq.client.system.user.service.SysUserService;
import com.geshanzsq.client.system.user.vo.SysUserVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import com.geshanzsq.framework.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 用户信息
 *
 * @author geshanzsq
 * @date 2022/3/26
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息")
public class SysUserInfoController extends BaseController {

    @Autowired
    private SysUserInfoService sysUserInfoService;
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public ResponseResult<SysUserVO> getUserInfo() {
        SysUserVO sysUserVo = sysUserInfoService.getUserInfo();
        return ResponseResult.success(sysUserVo);
    }

    @ApiOperation("更新信息")
    @PutMapping
    public ResponseResult update(@Valid @RequestBody SysUserUpdateDTO updateDTO) {
        SysUser sysUser = SysUserConverter.INSTANCE.convert(updateDTO);
        sysUser.setId(SecurityUtils.getUserId());
        sysUserService.updateById(sysUser);
        return ResponseResult.success();
    }


}
