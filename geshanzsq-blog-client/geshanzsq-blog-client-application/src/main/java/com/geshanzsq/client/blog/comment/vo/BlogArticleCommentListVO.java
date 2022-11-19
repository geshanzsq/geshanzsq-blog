package com.geshanzsq.client.blog.comment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章评论列表
 *
 * @author geshanzsq
 * @date 2022/10/23
 */
@Data
@ApiModel("文章评论列表")
public class BlogArticleCommentListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论 id")
    private Long id;

    @ApiModelProperty("上级 id")
    private Long parentId;

    @ApiModelProperty("姓名")
    private String nickName;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("是否置顶（1 是，2 否）")
    private String hasSticky;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("创建人用户 id")
    private Long createUserId;

    @ApiModelProperty("是否作者")
    private Boolean hasAuthor;

    @ApiModelProperty("子评论")
    private List<BlogArticleCommentListVO> children;

}
