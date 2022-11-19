package com.geshanzsq.admin.blog.picture.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.plugin.annotation.Query;
import com.geshanzsq.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片分页
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Data
@ApiModel("图片分页")
public class BlogPicturePageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类 id")
    private Long categoryId;

    @ApiModelProperty("图片原名称")
    @Query(QueryWay.LIKE)
    private String pictureOriginalName;
}
