package com.geshanzsq.client.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 相关文章
 *
 * @author geshanzsq
 * @date 2022/10/6
 */
@Data
@ApiModel("相关文章")
public class BlogArticleAboutVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章 id")
    private Long id;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("封面地址")
    private String coverUrl;

}
