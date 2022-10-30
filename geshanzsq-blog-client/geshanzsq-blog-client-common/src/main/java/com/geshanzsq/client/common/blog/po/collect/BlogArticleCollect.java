package com.geshanzsq.client.common.blog.po.collect;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章收藏
 *
 * @author geshanzsq
 * @date 2022/10/8
 */
@Data
public class BlogArticleCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    /**
     * 收藏 id
     */
    private Long id;

    /**
     * 文章 id
     */
    @TableField("fk_article_id")
    private Long articleId;

    /**
     * 用户 id
     */
    @TableField("fk_create_user_id")
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

}
