package com.geshanzsq.client.common.blog.po.tag;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客标签
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Data
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签 id
     */
    @TableId
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 点击数
     */
    private Long clickCount;

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
