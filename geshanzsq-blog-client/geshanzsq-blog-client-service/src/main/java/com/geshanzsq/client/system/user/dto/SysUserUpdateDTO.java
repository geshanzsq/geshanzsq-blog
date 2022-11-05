package com.geshanzsq.client.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户修改
 *
 * @author geshanzsq
 * @date 2022/10/20
 */
@Data
@ApiModel("用户修改")
public class SysUserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("性别，1 保密，2 男，3 女")
    private Integer sex;

    @ApiModelProperty("备注")
    private String remark;
}
