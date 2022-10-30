package com.geshanzsq.client.core.security.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录地址
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
@Data
@ApiModel("登录地址")
@NoArgsConstructor
@AllArgsConstructor
public class LoginUrlVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("链接")
    private String url;

}
