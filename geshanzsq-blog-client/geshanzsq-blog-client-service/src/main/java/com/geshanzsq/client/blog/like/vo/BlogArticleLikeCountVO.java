package com.geshanzsq.client.blog.like.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 文章点赞
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Data
@ApiModel("文章点赞数")
public class BlogArticleLikeCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章 id")
    private Long articleId;

    @ApiModelProperty("点赞数量")
    private Long count;

}
