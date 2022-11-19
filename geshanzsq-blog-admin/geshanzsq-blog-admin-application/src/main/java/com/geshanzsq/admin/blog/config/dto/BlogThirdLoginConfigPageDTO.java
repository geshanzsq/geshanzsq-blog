package com.geshanzsq.admin.blog.config.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.plugin.annotation.Query;
import com.geshanzsq.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 第三方登录配置分页
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Data
@ApiModel("第三方登录配置分页")
public class BlogThirdLoginConfigPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("第三方名称")
    @Query(QueryWay.LIKE)
    private String thirdName;

    @ApiModelProperty("第三方来源")
    @Query(QueryWay.LIKE)
    private String source;

    @ApiModelProperty("是否开启登录（1 是，2 否）")
    private Integer hasOpenLogin;
}
