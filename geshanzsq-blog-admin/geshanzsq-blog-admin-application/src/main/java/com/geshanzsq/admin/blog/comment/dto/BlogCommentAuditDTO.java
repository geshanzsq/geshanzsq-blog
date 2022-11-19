package com.geshanzsq.admin.blog.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评论审核
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Data
@ApiModel("评论审核")
public class BlogCommentAuditDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论 id", required = true)
    @NotNull(message = "评论 id 不能为空")
    private Long id;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(value = "状态（1 待审核，2 通过，3 驳回）", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

}
