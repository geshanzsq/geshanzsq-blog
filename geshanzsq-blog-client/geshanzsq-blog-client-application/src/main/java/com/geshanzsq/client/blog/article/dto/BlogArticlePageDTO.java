package com.geshanzsq.client.blog.article.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章分页
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
@Data
@ApiModel("文章分页")
public class BlogArticlePageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态（1 草稿，2 发布，3 下架）", hidden = true)
    private Integer status;

}
