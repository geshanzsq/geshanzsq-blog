package com.geshanzsq.system.role.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色分页查询
 *
 * @author geshanzsq
 * @date 2022/6/19
 */
@Data
@ApiModel("角色分页查询")
public class SysRolePageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("状态，1 正常，2 停用")
    private Integer status;

}
