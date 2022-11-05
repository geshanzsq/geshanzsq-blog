package com.geshanzsq.client.common.blog.po.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章内容
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Data
public class BlogArticleContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章 id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章内容
     */
    private String articleContent;

}
