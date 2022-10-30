package com.geshanzsq.client.core.security.auth.controller;

import com.geshanzsq.client.core.security.auth.service.ThirdLoginService;
import com.geshanzsq.client.core.security.auth.vo.LoginUrlVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第三方登录
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
@RestController
@Api(tags = "第三方登录")
@RequestMapping("/auth/third/login")
public class ThirdLoginController extends BaseController {

    @Autowired
    private ThirdLoginService authService;

    @ApiOperation("获取登录地址")
    @GetMapping("/getLoginUrl/{source}")
    public ResponseResult<LoginUrlVO> getLoginUrl(@PathVariable String source) {
        LoginUrlVO loginUrlVO = authService.getLoginUrl(source);
        return ResponseResult.success(loginUrlVO);
    }

    @ApiOperation("登录成功回调")
    @GetMapping("/callback/{source}")
    public void callback(@PathVariable String source, AuthCallback authCallback, HttpServletResponse response) throws IOException {
        authService.callback(source, authCallback, response);
    }

}
