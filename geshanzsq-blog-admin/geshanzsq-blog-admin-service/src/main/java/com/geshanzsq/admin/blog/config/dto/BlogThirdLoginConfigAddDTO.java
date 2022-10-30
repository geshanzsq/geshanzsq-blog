package com.geshanzsq.admin.blog.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 第三方登录配置新增
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Data
@ApiModel("第三方登录配置新增")
public class BlogThirdLoginConfigAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第三方名称", required = true)
    @NotBlank(message = "第三方名称不能为空")
    private String thirdName;

    @ApiModelProperty(value = "第三方来源", required = true)
    @NotBlank(message = "第三方来源不能为空")
    private String source;

    @ApiModelProperty(value = "第三方 id", required = true)
    @NotBlank(message = "第三方 id不能为空")
    private String clientId;

    @ApiModelProperty(value = "第三方密钥", required = true)
    @NotBlank(message = "第三方密钥不能为空")
    private String clientSecret;

    @ApiModelProperty(value = "是否开启登录（1 是，2 否）", required = true)
    @NotNull(message = "是否开启登录不能为空")
    private Integer hasOpenLogin;

}
