package com.geshanzsq.client.blog.about.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 关于我
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Data
@ApiModel("关于我")
public class BlogAboutMeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("内容")
    private String content;

}
