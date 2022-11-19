package com.geshanzsq.client.blog.like.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 新增点赞
 *
 * @author geshanzsq
 * @date 2022/10/8
 */
@Data
@ApiModel("新增点赞")
public class BlogArticleLikeAddDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章 id", required = true)
    @NotNull(message = "文章 id 不能为空")
    private Long articleId;
}
