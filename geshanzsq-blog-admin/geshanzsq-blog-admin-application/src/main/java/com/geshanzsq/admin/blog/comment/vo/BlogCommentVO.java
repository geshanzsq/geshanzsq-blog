package com.geshanzsq.admin.blog.comment.vo;

import com.geshanzsq.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/26
 */
@Data
@ApiModel("评论")
public class BlogCommentVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论 id")
    private Long id;

    @ApiModelProperty("文章 id")
    private Long articleId;

    @ApiModelProperty("是否置顶（1 是，2 否）")
    private Integer hasSticky;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态（1 待审核，2 通过，3 驳回）")
    private Integer status;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;
}
