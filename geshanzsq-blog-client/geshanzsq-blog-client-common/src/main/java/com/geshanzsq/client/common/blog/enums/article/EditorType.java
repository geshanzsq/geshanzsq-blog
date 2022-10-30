package com.geshanzsq.client.common.blog.enums.article;

/**
 * 编辑器类型
 *
 * @author geshanzsq
 * @date 2022/9/30
 */
public enum EditorType {
    /**
     * Markdown编辑器
     */
    MARKDOWN(1),

    /**
     * 富文本编辑器
     */
    RICH_TEXT(2)
    ;

    public final Integer code;

    EditorType(Integer code) {
        this.code = code;
    }
}
