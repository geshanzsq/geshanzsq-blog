package com.geshanzsq.admin.blog.comment.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 评论分页
 *
 * @author geshanzsq
 * @date 2022/10/26
 */
@Data
@ApiModel("评论分页")
public class BlogCommentPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态（1 待审核，2 通过，3 驳回）")
    private Integer status;
}
