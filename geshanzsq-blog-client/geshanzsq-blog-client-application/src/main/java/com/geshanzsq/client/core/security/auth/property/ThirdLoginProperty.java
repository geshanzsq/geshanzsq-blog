package com.geshanzsq.client.core.security.auth.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方登录
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "third-login")
public class ThirdLoginProperty {

    /**
     * 网站地址
     */
    private String siteUrl;

    /**
     * 登录回调地址
     */
    private String callbackUrl;

    /**
     * 错误地址
     */
    private String errorUrl;

    /**
     * 登录页面地址
     */
    private String loginUrl;

}
