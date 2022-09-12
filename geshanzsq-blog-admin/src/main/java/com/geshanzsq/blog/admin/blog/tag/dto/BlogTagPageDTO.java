package com.geshanzsq.blog.admin.blog.tag.dto;

import com.geshanzsq.blog.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客标签分页
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Data
@ApiModel("博客标签分页")
public class BlogTagPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标签名称")
    private String tagName;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;
}
