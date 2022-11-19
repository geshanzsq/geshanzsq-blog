package com.geshanzsq.admin.blog.article.vo;

import com.geshanzsq.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/8/18
 */
@Data
@ApiModel("文章")
public class BlogArticleVO extends BaseVO implements Serializable {

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

    @ApiModelProperty("分类 id")
    private Long categoryId;

    @ApiModelProperty("赞赏是否开启（1 开启，2 不开启）")
    private Integer hasAppreciation;

    @ApiModelProperty("评论是否开启（1 开启，2 不开启）")
    private Integer hasComment;

    @ApiModelProperty("转载声明是否开启（1 开启，2 不开启）")
    private Integer hasReprintStatement;

    @ApiModelProperty("是否置顶（1 是，2 否）")
    private Integer hasSticky;

    @ApiModelProperty("浏览数量")
    private Long viewCount;

    @ApiModelProperty("编辑器类型（1 markdown编辑器，2 富文本编辑器）")
    private Integer editorType;

    @ApiModelProperty("发布时间")
    private Date gmtPublish;

    @ApiModelProperty("最后发布时间")
    private Date gmtLastPublish;

    @ApiModelProperty("状态（1 草稿，2 发布，3 下架）")
    private Integer status;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("标签列表")
    private List<BlogTagVO> tags;

    @ApiModelProperty("作者")
    private BlogArticleAuthorVO author;

    @ApiModelProperty("推荐等级")
    private List<Integer> recommendLevels;

}
