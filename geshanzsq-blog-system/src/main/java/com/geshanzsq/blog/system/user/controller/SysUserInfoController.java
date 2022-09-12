package com.geshanzsq.blog.system.user.controller;

import com.geshanzsq.blog.common.core.web.response.ResponseResult;
import com.geshanzsq.blog.common.framework.web.controller.BaseController;
import com.geshanzsq.blog.system.menu.service.SysMenuService;
import com.geshanzsq.blog.system.user.service.SysUserInfoService;
import com.geshanzsq.blog.system.user.vo.RouterVO;
import com.geshanzsq.blog.system.user.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private SysMenuService sysMenuService;

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public ResponseResult<SysUserVO> getUserInfo() {
        SysUserVO sysUserVo = sysUserInfoService.getUserInfo();
        return ResponseResult.success(sysUserVo);
    }

    @ApiOperation("获取菜单路由")
    @GetMapping("/getRouters")
    public ResponseResult<List<RouterVO>> getRouters() {
        List<RouterVO> routers = sysMenuService.getRouters();
        return ResponseResult.success(routers);
    }

}
