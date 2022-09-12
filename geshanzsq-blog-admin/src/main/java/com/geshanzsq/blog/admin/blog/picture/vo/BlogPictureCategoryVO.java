package com.geshanzsq.blog.admin.blog.picture.vo;

import com.geshanzsq.blog.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Data
@ApiModel("图片分类")
public class BlogPictureCategoryVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片分类 id")
    private Long id;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;

}
