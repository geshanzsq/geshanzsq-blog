package com.geshanzsq.client.blog.board.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 开启状态
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
@Data
@Builder
@ApiModel("开启状态")
public class BoardOpenStatusVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态")
    private Boolean status;
}
