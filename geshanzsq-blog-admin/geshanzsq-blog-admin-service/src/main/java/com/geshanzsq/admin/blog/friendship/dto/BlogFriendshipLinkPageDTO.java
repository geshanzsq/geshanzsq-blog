package com.geshanzsq.admin.blog.friendship.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 友情链接分页
 *
 * @author geshanzsq
 * @date 2022/10/6
 */
@Data
@ApiModel("友情链接分页")
public class BlogFriendshipLinkPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("链接名称")
    private String linkName;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;
}
