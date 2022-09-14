package com.geshanzsq.admin.blog.article.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文章分页
 *
 * @author geshanzsq
 * @date 2022/8/18
 */
@Data
@ApiModel("文章分页")
public class BlogArticlePageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章标题或描述")
    private String articleTitleDescription;

    @ApiModelProperty("所属分类 id")
    private Long categoryId;

    @ApiModelProperty("所属标签 ids")
    private List<Long> tagIds;

    @ApiModelProperty("状态（1 草稿，2 发布，3 下架）")
    private Integer status;

    @ApiModelProperty("标识（1 原创，2 转载，3 整理）")
    private Integer flag;

}
