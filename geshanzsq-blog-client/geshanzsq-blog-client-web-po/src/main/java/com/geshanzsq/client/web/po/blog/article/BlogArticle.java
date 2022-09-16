package com.geshanzsq.client.web.po.blog.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/8/18
 */
@Data
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章 id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章描述
     */
    private String articleDescription;

    /**
     * 封面地址
     */
    private String coverUrl;

    /**
     * 标识（1 原创，2 转载，3 整理）
     */
    private Integer flag;

    /**
     * 分类 id
     */
    @TableField("fk_category_id")
    private Long categoryId;

    /**
     * 赞赏是否开启（1 开启，2 不开启）
     */
    private Integer hasAppreciation;

    /**
     * 评论是否开启（1 开启，2 不开启）
     */
    private Integer hasComment;

    /**
     * 转载声明是否开启（1 开启，2 不开启）
     */
    private Integer hasReprintStatement;

    /**
     * 是否置顶（1 是，2 否）
     */
    private Integer hasSticky;

    /**
     * 浏览数量
     */
    private Long viewCount;

    /**
     * 编辑器类型（1 markdown编辑器，2 富文本编辑器）
     */
    private Integer editorType;

    /**
     * 发布时间
     */
    private Date gmtPublish;

    /**
     * 最后发布时间
     */
    private Date gmtLastPublish;

    /**
     * 状态（1 草稿，2 发布，3 下架）
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
