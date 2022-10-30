package com.geshanzsq.admin.blog.config.vo;

import com.geshanzsq.common.core.web.vo.BaseVO;
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
@ApiModel("第三方登录配置")
public class BlogThirdLoginConfigVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("配置 id")
    private Long id;

    @ApiModelProperty("第三方名称")
    private String thirdName;

    @ApiModelProperty("第三方来源")
    private String source;

    @ApiModelProperty("第三方 id")
    private String clientId;

    @ApiModelProperty("第三方密钥")
    private String clientSecret;

    @ApiModelProperty("是否开启登录（1 是，2 否）")
    private Integer hasOpenLogin;

}
