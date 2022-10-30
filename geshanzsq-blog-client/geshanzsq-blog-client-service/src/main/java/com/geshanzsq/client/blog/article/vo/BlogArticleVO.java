package com.geshanzsq.client.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章详情
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
@Data
@ApiModel("文章详情")
public class BlogArticleVO implements Serializable {

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

    @ApiModelProperty("赞赏是否开启（1 开启，2 不开启）")
    private Integer hasAppreciation;

    @ApiModelProperty("评论是否开启（1 开启，2 不开启）")
    private Integer hasComment;

    @ApiModelProperty("转载声明是否开启（1 开启，2 不开启）")
    private Integer hasReprintStatement;

    @ApiModelProperty("浏览数量")
    private Long viewCount;

    @ApiModelProperty("发布时间")
    private Date gmtPublish;

    @ApiModelProperty("最后发布时间")
    private Date gmtLastPublish;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("标签名称列表")
    private List<String> tagNames;

    @ApiModelProperty("作者")
    private BlogArticleAuthorVO author;

    @ApiModelProperty("点赞数")
    private Long likeCount;

    @ApiModelProperty("收藏数")
    private Long collectCount;

}
