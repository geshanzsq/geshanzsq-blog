package com.geshanzsq.client.core.security.auth.service;

import com.geshanzsq.client.core.security.auth.vo.LoginUrlVO;
import me.zhyd.oauth.model.AuthCallback;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第三方登录
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
public interface ThirdLoginService {

    /**
     * 获取登录地址
     *
     * @param source 第三方登录来源
     */
    LoginUrlVO getLoginUrl(String source);

    /**
     * 登录成功回调
     *
     * @param source 第三方登录来源
     * @param authCallback
     * @param response
     */
    void callback(String source, AuthCallback authCallback, HttpServletResponse response) throws IOException;
}
