package com.geshanzsq.blog.system.api.dto;

import com.geshanzsq.blog.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 接口分页查询
 *
 * @author geshanzsq
 * @date 2022/6/24
 */
@Data
@ApiModel("接口分页")
public class SysApiPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("接口名称")
    private String apiName;

    @ApiModelProperty("接口地址")
    private String apiUrl;

    @ApiModelProperty("接口请求方式（如：get，post）")
    private String apiMethod;

    @ApiModelProperty(value = "所属分类", required = true)
    @NotNull(message = "所属分类不能为空")
    private Long apiCategoryId;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;
}
