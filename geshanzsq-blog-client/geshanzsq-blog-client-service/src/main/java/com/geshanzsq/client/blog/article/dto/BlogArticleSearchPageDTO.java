package com.geshanzsq.client.blog.article.dto;

import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章查询分页
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
@Data
@ApiModel("文章查询分页")
public class BlogArticleSearchPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章标题、描述、内容")
    private String articleTitle;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("标签名称")
    private String tagName;

    @ApiModelProperty("作者姓名")
    private String authorNickName;

    @ApiModelProperty("标识（1 原创，2 转载，3 整理）")
    private Integer flag;

    @ApiModelProperty("归档时间线")
    private String archiveGmPublishLine;

    @ApiModelProperty(value = "状态（1 草稿，2 发布，3 下架）", hidden = true)
    private Integer status;

}
