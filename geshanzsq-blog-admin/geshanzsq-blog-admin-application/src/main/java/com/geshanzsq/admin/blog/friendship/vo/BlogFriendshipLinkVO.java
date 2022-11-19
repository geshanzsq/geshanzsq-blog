package com.geshanzsq.admin.blog.friendship.vo;

import com.geshanzsq.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Data
@ApiModel("友情链接")
public class BlogFriendshipLinkVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("链接 id")
    private Long id;

    @ApiModelProperty("链接名称")
    private String linkName;

    @ApiModelProperty("链接简介")
    private String linkDescription;

    @ApiModelProperty("链接地址")
    private String linkUrl;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;


}
