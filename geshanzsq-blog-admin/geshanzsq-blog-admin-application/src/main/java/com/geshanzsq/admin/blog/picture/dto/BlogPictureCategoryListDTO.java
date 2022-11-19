package com.geshanzsq.admin.blog.picture.dto;

import com.geshanzsq.common.framework.mybatis.plugin.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片分类列表
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Data
@ApiModel("图片分类列表")
public class BlogPictureCategoryListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;

    @ApiModelProperty("排序列，多个用逗号分开")
    @Query(ignore = true)
    private String orderColumn;

    @ApiModelProperty("排序类型(asc 或 desc)，多个用逗号分开")
    @Query(ignore = true)
    private String orderType;

}
