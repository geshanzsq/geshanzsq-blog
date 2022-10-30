package com.geshanzsq.client.common.blog.po.comment;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
@Data
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论 id
     */
    @TableId
    private Long id;

    /**
     * 上级 id
     */
    private Long parentId;

    /**
     * 文章 id
     */
    @TableField("fk_article_id")
    private Long articleId;

    /**
     * 是否置顶（1 是，2 否）
     */
    private Integer hasSticky;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（1 待审核，2 通过，3 驳回）
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
