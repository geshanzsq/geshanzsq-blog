package com.geshanzsq.client.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章推荐
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
@Data
@ApiModel("文章推荐")
public class BlogArticleRecommendVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章 id")
    private Long id;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("封面地址")
    private String coverUrl;

    @ApiModelProperty("发布时间")
    private Date gmtPublish;

}
