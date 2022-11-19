package com.geshanzsq.admin.blog.friendship.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 友情链接修改
 *
 * @author geshanzsq
 * @date 2022/10/6
 */
@Data
@ApiModel("友情链接修改")
public class BlogFriendshipLinkUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "链接 id", required = true)
    private Long id;

    @ApiModelProperty(value = "链接名称", required = true)
    private String linkName;

    @ApiModelProperty("链接简介")
    private String linkDescription;

    @ApiModelProperty(value = "链接地址", required = true)
    private String linkUrl;

    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;

    @ApiModelProperty(value = "状态（1 正常，2 停用）", required = true)
    private Integer status;

}
