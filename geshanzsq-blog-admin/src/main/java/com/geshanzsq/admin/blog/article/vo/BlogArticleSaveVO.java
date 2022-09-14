package com.geshanzsq.admin.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章保存
 *
 * @author geshanzsq
 * @date 2022/9/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文章保存")
public class BlogArticleSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章 id")
    private Long id;
}
