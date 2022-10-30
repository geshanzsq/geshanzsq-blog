package com.geshanzsq.client.blog.tag.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 标签列表
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Data
@ApiModel("标签列表")
public class BlogTagListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标签名称")
    private String tagName;

}
