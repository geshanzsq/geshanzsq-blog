package com.geshanzsq.client.blog.collect.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * 文章收藏
 *
 * @author geshanzsq
 * @date 2022/10/8
 */
@Data
public class BlogArticleCollectCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章 id")
    private Long articleId;

    @ApiModelProperty("收藏数量")
    private Long count;
}
