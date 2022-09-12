package com.geshanzsq.blog.admin.blog.picture.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片分类
 *
 * @author geshanzsq
 * @date 2022/8/23
 */
@Data
public class BlogPictureCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片分类 id
     */
    @TableId
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态（1 正常，2 停用）
     */
    private Integer status;

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
