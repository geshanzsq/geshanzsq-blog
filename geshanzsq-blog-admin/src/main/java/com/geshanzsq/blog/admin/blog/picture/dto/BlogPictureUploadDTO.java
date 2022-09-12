package com.geshanzsq.blog.admin.blog.picture.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 图片上传
 *
 * @author geshanzsq
 * @date 2022/9/2
 */
@ApiModel("图片上传")
@Data
public class BlogPictureUploadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类 id")
    private Long categoryId;

    @ApiModelProperty(value = "文件流", required = true)
    @NotNull(message = "请选择需要上传的文件")
    private MultipartFile file;
}
