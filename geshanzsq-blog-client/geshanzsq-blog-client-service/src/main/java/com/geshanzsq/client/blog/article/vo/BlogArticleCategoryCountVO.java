package com.geshanzsq.client.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类数
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Data
@ApiModel("文章分类数")
public class BlogArticleCategoryCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类 id")
    private Long categoryId;

    @ApiModelProperty("文章数量")
    private Long articleCount;
}
