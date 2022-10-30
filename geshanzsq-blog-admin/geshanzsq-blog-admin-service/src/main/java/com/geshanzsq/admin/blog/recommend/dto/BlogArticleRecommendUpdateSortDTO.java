package com.geshanzsq.admin.blog.recommend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 更新排序
 *
 * @author geshanzsq
 * @date 2022/10/29
 */
@Data
@ApiModel("更新排序")
public class BlogArticleRecommendUpdateSortDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("推荐 id")
    private Long id;

    @ApiModelProperty("排序")
    private Integer sort;
}
