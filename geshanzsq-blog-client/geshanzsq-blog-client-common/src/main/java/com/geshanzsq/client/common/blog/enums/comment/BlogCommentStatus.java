package com.geshanzsq.client.common.blog.enums.comment;

/**
 * 评论状态
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
public enum BlogCommentStatus {

    /**
     * 待审核
     */
    AUDIT(1),

    /**
     * 已通过
     */
    PASS(2),

    /**
     * 驳回
     */
    REJECT(3)

    ;

    public final Integer code;

    BlogCommentStatus(Integer code) {
        this.code = code;
    }
}
