package com.geshanzsq.blog.common.framework.mybatis.page.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页对象
 *
 * @author geshanzsq
 * @date 2022/3/27
 */
@ApiModel("分页对象")
@Data
public class PageDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("每页显示记录数")
    private Long pageSize;

    @ApiModelProperty("起始页")
    private Long pageNum;

    @ApiModelProperty("排序列，多个用逗号分开")
    private String orderColumn;

    @ApiModelProperty("排序类型(asc 或 desc)，多个用逗号分开")
    private String orderType;

}
