package com.geshanzsq.admin.blog.about.vo;

import com.geshanzsq.common.core.web.vo.BaseVO;
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
public class BlogAboutMeVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关于 id")
    private Long id;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("编辑器类型（1 markdown编辑器，2 富文本编辑器）")
    private Integer editorType;

}
