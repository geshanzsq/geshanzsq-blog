package com.geshanzsq.client.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
@Data
@ApiModel("文章列表")
public class BlogArticlePageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章 id")
    private Long id;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章描述")
    private String articleDescription;

    @ApiModelProperty("封面地址")
    private String coverUrl;

    @ApiModelProperty("标识（1 原创，2 转载，3 整理）")
    private Integer flag;

    @ApiModelProperty("是否置顶（1 是，2 否）")
    private Integer hasSticky;

    @ApiModelProperty("浏览数量")
    private Long viewCount;

    @ApiModelProperty("发布时间")
    private Date gmtPublish;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("作者")
    private BlogArticleAuthorVO author;

    @ApiModelProperty("点赞数")
    private Long likeCount;

}
