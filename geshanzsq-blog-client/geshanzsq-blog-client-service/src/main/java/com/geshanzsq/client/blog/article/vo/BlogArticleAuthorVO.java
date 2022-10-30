package com.geshanzsq.client.blog.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章作者
 *
 * @author geshanzsq
 * @date 2022/8/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文章作者")
public class BlogArticleAuthorVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("姓名")
    private String nickName;

    @ApiModelProperty("头像")
    private String avatar;

}
