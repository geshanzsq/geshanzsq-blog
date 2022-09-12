package com.geshanzsq.blog.admin.blog.article.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Data
public class BlogArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标签 id
     */
    @TableId
    private Long id;

    /**
     * 文章 id
     */
    @TableField("fk_article_id")
    private Long articleId;

    /**
     * 标签 id
     */
    @TableField("fk_tag_id")
    private Long tagId;

}
