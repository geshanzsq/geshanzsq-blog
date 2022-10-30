package com.geshanzsq.client.common.blog.po.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客第三方用户
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
@Data
public class BlogThirdUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客用户 id
     */
    @TableId
    private Long id;

    /**
     * 用户 id
     */
    @TableField("fk_user_id")
    private Long userId;

    /**
     * 第三方账号来源
     */
    private String source;

    /**
     * 第三方平台 uuid
     */
    private String uuid;

    /**
     * 第三方用户
     */
    private String username;

}
