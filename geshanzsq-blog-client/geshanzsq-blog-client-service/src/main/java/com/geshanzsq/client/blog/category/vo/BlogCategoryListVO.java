package com.geshanzsq.client.blog.category.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类列表
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Data
@ApiModel("分类列表")
public class BlogCategoryListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类 id")
    private Long id;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("文章数")
    private Long articleCount;

}
