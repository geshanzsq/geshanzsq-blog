package com.geshanzsq.client.blog.friendship.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 友情链接列表
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Data
@ApiModel("友情链接列表")
public class BlogFriendshipLinkListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("链接名称")
    private String linkName;

    @ApiModelProperty("链接地址")
    private String linkUrl;

}
