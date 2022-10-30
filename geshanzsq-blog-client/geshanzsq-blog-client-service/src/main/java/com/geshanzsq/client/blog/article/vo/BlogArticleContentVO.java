package com.geshanzsq.client.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章内容
 *
 * @author geshanzsq
 * @date 2022/10/30
 */
@Data
@ApiModel("文章内容")
public class BlogArticleContentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章内容")
    private String articleContent;

}
