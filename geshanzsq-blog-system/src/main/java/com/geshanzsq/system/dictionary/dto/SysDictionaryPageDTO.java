package com.geshanzsq.system.dictionary.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典分页
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
@Data
@ApiModel("字典分页")
public class SysDictionaryPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典名称")
    private String dictionaryName;

    @ApiModelProperty("字典编码")
    private String dictionaryCode;

    @ApiModelProperty("状态，1 正常，2 停用")
    private Integer status;
}
