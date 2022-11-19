package com.geshanzsq.client.blog.comment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论分页
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
@Data
@ApiModel("评论分页")
public class BlogCommentPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论 id")
    private Long id;

    @ApiModelProperty("文章 id")
    private Long articleId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

}
