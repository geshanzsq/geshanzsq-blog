package com.geshanzsq.blog.admin.blog.picture.vo;

import com.geshanzsq.blog.common.core.web.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Data
@ApiModel("图片")
public class BlogPictureVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片 id")
    private Long id;

    @ApiModelProperty("图片原名称")
    private String pictureOriginalName;

    @ApiModelProperty("图片路径")
    private String picturePath;

    @ApiModelProperty("图片 md5")
    private String pictureMd5;

    @ApiModelProperty("分类 id")
    private Long categoryId;

}
