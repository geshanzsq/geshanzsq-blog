package com.geshanzsq.admin.blog.about;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 关于我编辑
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Data
@ApiModel("关于我编辑")
public class BlogAboutMeEditDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关于 id")
    private Long id;

    @ApiModelProperty("内容")
    @NotBlank(message = "内容不能为空")
    private String content;

    @ApiModelProperty("编辑器类型（1 markdown编辑器，2 富文本编辑器）")
    private Integer editorType;
}
