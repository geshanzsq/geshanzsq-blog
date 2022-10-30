package com.geshanzsq.admin.blog.recommend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 推荐列表
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Data
@ApiModel("推荐列表")
public class BlogArticleRecommendListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("推荐等级")
    private Integer recommendLevel;

}
