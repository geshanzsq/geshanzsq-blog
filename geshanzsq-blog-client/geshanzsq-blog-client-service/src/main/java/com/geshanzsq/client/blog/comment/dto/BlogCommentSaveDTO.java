package com.geshanzsq.client.blog.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评论新增
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
@Data
@ApiModel("评论新增")
public class BlogCommentSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章 id")
    @NotNull(message = "文章 id 不能为空")
    private Long articleId;

    @ApiModelProperty("上级 id")
    private Long parentId;

    @ApiModelProperty("评论内容")
    @NotBlank(message = "评论内容不能为空")
    private String commentContent;


}
