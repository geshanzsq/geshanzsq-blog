package com.geshanzsq.client.blog.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Data
@ApiModel("第三方登录配置列表")
public class BlogThirdLoginConfigListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("配置 id")
    private Long id;

    @ApiModelProperty("第三方名称")
    private String thirdName;

    @ApiModelProperty("第三方来源")
    private String source;

}
