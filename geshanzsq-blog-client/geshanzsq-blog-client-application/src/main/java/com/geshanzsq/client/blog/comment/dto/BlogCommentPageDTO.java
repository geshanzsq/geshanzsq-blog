package com.geshanzsq.client.blog.comment.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.plugin.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章用户评论分页
 *
 * @author geshanzsq
 * @date 2022/11/5
 */
@Data
@ApiModel("文章用户评论分页")
public class BlogCommentPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户 id", hidden = true)
    @Query(empty = true)
    private Long createUserId;

}
