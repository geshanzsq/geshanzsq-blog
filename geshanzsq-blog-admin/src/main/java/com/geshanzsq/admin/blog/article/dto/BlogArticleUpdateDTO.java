package com.geshanzsq.admin.blog.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 文章修改
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Data
@ApiModel("文章修改")
public class BlogArticleUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章 id", required = true)
    @NotNull(message = "文章 id 不能为空")
    private Long id;

    @ApiModelProperty(value = "文章标题", required = true)
    @NotBlank(message = "标题不能为空")
    private String articleTitle;

    @ApiModelProperty(value = "文章内容", required = true)
    @NotBlank(message = "内容不能为空")
    private String articleContent;

    @ApiModelProperty(value = "文章描述", required = true)
    @NotBlank(message = "描述不能为空")
    private String articleDescription;

    @ApiModelProperty("封面地址")
    private String coverUrl;

    @ApiModelProperty(value = "标识（1 原创，2 转载，3 整理）", required = true)
    @NotNull(message = "标识不能为空")
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

    @ApiModelProperty("编辑器类型（1 markdown编辑器，2 富文本编辑器）")
    private Integer editorType;

    @ApiModelProperty("状态（1 草稿，2 发布，3 下架）")
    private Integer status;

    @ApiModelProperty("标签 id 集合")
    private List<Long> tagIds;

    @ApiModelProperty("推荐等级")
    private List<Integer> recommendLevels;

}
