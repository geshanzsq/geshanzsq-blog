package com.geshanzsq.client.core.security.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 第三方登录认证响应
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
@Data
@ApiModel("第三方登录认证响应")
public class ThirdLoginAuthResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("平台 uuid")
    private String uuid;

    @ApiModelProperty("平台来源")
    private String source;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("qq")
    private String qq;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

}
