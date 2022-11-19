package com.geshanzsq.admin.blog.picture.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Data
public class BlogPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId
    private Long id;

    /**
     * 图片原名称
     */
    private String pictureOriginalName;

    /**
     * 图片路径
     */
    private String picturePath;

    /**
     * 图片 md5
     */
    private String pictureMd5;

    /**
     * 分类 id
     */
    @TableField("fk_category_id")
    private Long categoryId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人用户 id
     */
    @TableField("fk_create_user_id")
    private Long createUserId;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 修改人用户 id
     */
    @TableField("fk_modify_user_id")
    private Long modifyUserId;

}
