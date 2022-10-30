package com.geshanzsq.client.core.security.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.geshanzsq.admin.system.common.enums.user.UserType;
import com.geshanzsq.admin.system.common.po.user.SysUser;
import com.geshanzsq.client.blog.config.service.BlogThirdLoginConfigService;
import com.geshanzsq.client.blog.user.mapstruct.BlogThirdUserConverter;
import com.geshanzsq.client.blog.user.service.BlogThirdUserService;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import com.geshanzsq.client.common.blog.po.user.BlogThirdUser;
import com.geshanzsq.client.core.security.auth.property.ThirdLoginProperty;
import com.geshanzsq.client.core.security.auth.service.ThirdLoginService;
import com.geshanzsq.client.core.security.auth.vo.LoginUrlVO;
import com.geshanzsq.client.core.security.auth.vo.ThirdLoginAuthResponseVO;
import com.geshanzsq.client.system.user.mapstruct.SysUserConverter;
import com.geshanzsq.client.system.user.service.SysUserService;
import com.geshanzsq.common.core.enums.YesNo;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.id.IdWorker;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.framework.security.domain.LoginUserDetail;
import com.geshanzsq.framework.security.service.TokenService;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第三方登录
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
@Service
public class ThirdLoginServiceImpl implements ThirdLoginService {

    private final static Logger log = LoggerFactory.getLogger(ThirdLoginServiceImpl.class);

    @Autowired
    private ThirdLoginProperty thirdLoginProperty;

    @Autowired
    private BlogThirdUserService blogThirdUserService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BlogThirdLoginConfigService blogThirdLoginConfigService;

    /**
     * 获取登录地址
     *
     * @param source 第三方登录来源
     */
    @Override
    public LoginUrlVO getLoginUrl(String source) {
        // 回调地址
        String callbackUrl = thirdLoginProperty.getSiteUrl() + thirdLoginProperty.getCallbackUrl() + source.toLowerCase();
        // 获取认证请求
        AuthRequest authRequest = getAuthRequest(source, callbackUrl);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        return new LoginUrlVO(authorizeUrl);
    }

    /**
     * 登录成功回调
     *
     * @param source 第三方登录来源
     * @param authCallback
     * @param response
     */
    @Override
    public void callback(String source, AuthCallback authCallback, HttpServletResponse response) throws IOException {
        // 回调地址
        String callbackUrl = thirdLoginProperty.getSiteUrl() + thirdLoginProperty.getCallbackUrl() + source.toLowerCase();
        // 获取认证请求
        AuthRequest authRequest = getAuthRequest(source, callbackUrl);
        // 获取第三方用户信息
        AuthResponse authResponse = authRequest.login(authCallback);
        int successCode = 2000;
        if (authResponse.getCode() != successCode) {
            String redirectUrl = thirdLoginProperty.getSiteUrl() + thirdLoginProperty.getErrorUrl();
            response.sendRedirect(redirectUrl);
            return;
        }

        ThirdLoginAuthResponseVO authResponseData = JSONObject.parseObject(
                JSONObject.toJSONString(authResponse.getData()),ThirdLoginAuthResponseVO.class);
        if (authResponseData == null) {
            log.error("获取第三方用户信息为空，来源：{}", source);
            String redirectUrl = thirdLoginProperty.getSiteUrl() + thirdLoginProperty.getErrorUrl();
            response.sendRedirect(redirectUrl);
            return;
        }

        // 1、获取当前第三方用户
        BlogThirdUser blogThirdUser = blogThirdUserService.getByUuid(authResponseData.getUuid());
        if (blogThirdUser == null) {
            // 当前系统没有此第三方用户，添加
            blogThirdUser = BlogThirdUserConverter.INSTANCE.convert(authResponseData);
            blogThirdUser.setSource(source);
            blogThirdUser.setUserId(IdWorker.nextId());
            blogThirdUserService.save(blogThirdUser);
        }

        // 2、获取用户信息
        SysUser sysUser = sysUserService.getById(blogThirdUser.getUserId());
        if (sysUser == null) {
            // 当前系统没有此用户，添加
            sysUser = SysUserConverter.INSTANCE.convert(authResponseData);
            sysUser.setId(blogThirdUser.getUserId());
            sysUser.setUserType(UserType.BLOG.code);
            sysUser.setUsername(StrUtils.format("{source}-{uuid}", source, authResponseData.getUuid()));
            sysUserService.save(sysUser);
        }

        // 3、创建登录令牌
        LoginUserDetail loginUserDetail = SysUserConverter.INSTANCE.convertDetail(sysUser);
        String token = tokenService.createLoginUser(loginUserDetail);

        // 4、跳转地址
        String redirectUrl = StrUtils.format("{siteUrl}{loginUrl}{token}",
                thirdLoginProperty.getSiteUrl(), thirdLoginProperty.getLoginUrl(), token);
        response.sendRedirect(redirectUrl);
    }

    /**
     * 获取认证请求
     *
     * @param source 第三方登录来源
     * @param callbackUrl 回调地址
     */
    private AuthRequest getAuthRequest(String source, String callbackUrl) {
        // 转换为小写
        source = source.toLowerCase();
        BlogThirdLoginConfig blogThirdLoginConfig = blogThirdLoginConfigService.getBySource(source);
        if (blogThirdLoginConfig == null || !YesNo.YES.code.equals(blogThirdLoginConfig.getHasOpenLogin())) {
            throw new ParamException(MessageUtils.message("third.login.not.support"));
        }
        AuthRequest authRequest = null;

        String clientId = blogThirdLoginConfig.getClientId();
        String clientSecret = blogThirdLoginConfig.getClientSecret();

        switch (source) {
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(callbackUrl)
                        .build());
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(callbackUrl)
                        .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .redirectUri(callbackUrl)
                        .build());
                break;
            default:
                throw new ParamException(MessageUtils.message("third.login.not.support"));
        }
        return authRequest;
    }
}
