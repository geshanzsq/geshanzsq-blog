package com.geshanzsq.client.blog.config.controller;


import com.geshanzsq.client.blog.config.service.BlogThirdLoginConfigService;
import com.geshanzsq.client.blog.config.vo.BlogThirdLoginConfigListVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Api(tags = "第三方登录配置")
@RestController
@RequestMapping("/third/config")
public class BlogThirdLoginConfigController extends BaseController {

    @Autowired
    private BlogThirdLoginConfigService blogThirdLoginConfigService;

    @ApiOperation("列表")
    @GetMapping("/list")
    public ResponseResult<List<BlogThirdLoginConfigListVO>> list() {
        List<BlogThirdLoginConfigListVO> list = blogThirdLoginConfigService.listConfig();
        return ResponseResult.success(list);
    }

}
