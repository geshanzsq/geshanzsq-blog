package com.geshanzsq.client.common.blog.po.friendship;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Data
public class BlogFriendshipLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    /**
     * 链接 id
     */
    private Long id;

    /**
     * 链接名称
     */
    private String linkName;

    /**
     * 链接简介
     */
    private String linkDescription;

    /**
     * 链接地址
     */
    private String linkUrl;

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
