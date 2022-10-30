package com.geshanzsq.admin.system.common.enums.user;

/**
 * 用户类型
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
public enum UserType {

    /**
     * 后台用户
     */
    ADMIN(1),

    /**
     * 博客用户
     */
    BLOG(2)
    ;

    public final Integer code;

    UserType(Integer code) {
        this.code = code;
    }
}
