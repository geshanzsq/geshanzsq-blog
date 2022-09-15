package com.geshanzsq.admin.blog.category.vo;

import com.geshanzsq.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客分类
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Data
@ApiModel("博客分类")
public class BlogCategoryVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类 id")
    private Long id;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("点击数")
    private Long clickCount;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;

}
