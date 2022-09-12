package com.geshanzsq.blog.framework.security.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * token 配置
 *
 * @author geshanzsq
 * @date 2022/3/23
 */
@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenProperty {

    /**
     * 自定义令牌标识
     */
    private String header;

    /**
     * 令牌有效期，默认为秒
     */
    private long expireTime;

    /**
     * 令牌参数
     */
    private String tokenParam;

    /**
     * 令牌前缀
     */
    private String tokenPrefix;

}
