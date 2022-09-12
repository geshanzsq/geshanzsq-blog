package com.geshanzsq.blog.system.menu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询菜单
 *
 * @author geshanzsq
 * @date 2022/6/12
 */
@Data
@ApiModel("查询菜单")
public class SysMenuListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单状态")
    private Integer status;

}
