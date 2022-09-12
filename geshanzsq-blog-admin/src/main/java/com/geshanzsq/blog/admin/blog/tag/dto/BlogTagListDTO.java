package com.geshanzsq.blog.admin.blog.tag.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 标签列表
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Data
@ApiModel("标签列表")
public class BlogTagListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;

}
