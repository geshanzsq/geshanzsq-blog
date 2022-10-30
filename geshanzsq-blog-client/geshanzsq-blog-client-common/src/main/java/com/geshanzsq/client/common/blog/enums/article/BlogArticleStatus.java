package com.geshanzsq.client.common.blog.enums.article;

/**
 * 文章状态
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
public enum BlogArticleStatus {
    /**
     * 草稿
     */
    DRAFT(1),

    /**
     * 发布
     */
    PUBLISH(2),

    /**
     * 下架
     */
    PULL_SHELVES(3)
    ;

    public final Integer code;

    BlogArticleStatus(Integer code) {
        this.code = code;
    }
}
