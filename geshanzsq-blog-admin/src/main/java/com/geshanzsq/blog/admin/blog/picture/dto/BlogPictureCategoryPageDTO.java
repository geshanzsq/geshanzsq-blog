package com.geshanzsq.blog.admin.blog.picture.dto;

import com.geshanzsq.blog.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客图片分类分页
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Data
@ApiModel("博客图片分类分页")
public class BlogPictureCategoryPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("状态（1 正常，2 停用）")
    private Integer status;

}
