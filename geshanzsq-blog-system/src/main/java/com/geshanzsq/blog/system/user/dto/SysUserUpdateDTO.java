package com.geshanzsq.blog.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户修改
 *
 * @author geshanzsq
 * @date 2022/6/18
 */
@Data
@ApiModel("用户修改")
public class SysUserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户 id", required = true)
    @NotNull(message = "用户 id 不能为空")
    private Long id;

    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "昵称 不能为空")
    private String nickName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("性别，1 保密，2 男，3 女")
    private Integer sex;

    @ApiModelProperty("状态，1 正常，2 停用")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
