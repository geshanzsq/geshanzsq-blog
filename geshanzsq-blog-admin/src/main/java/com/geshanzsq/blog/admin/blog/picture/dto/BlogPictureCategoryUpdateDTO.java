package com.geshanzsq.blog.admin.blog.picture.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 博客图片分类分页
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Data
@ApiModel("博客图片分类修改")
public class BlogPictureCategoryUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片分类 id", required = true)
    @NotNull(message = "图片分类 id 不能为空")
    private Long id;

    @ApiModelProperty(value = "分类名称", required = true)
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty(value = "状态（1 正常，2 停用）", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;
}
