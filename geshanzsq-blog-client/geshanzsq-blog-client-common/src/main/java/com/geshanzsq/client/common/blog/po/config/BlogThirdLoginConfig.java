package com.geshanzsq.client.common.blog.po.config;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Data
public class BlogThirdLoginConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置 id
     */
    @TableId
    private Long id;

    /**
     * 第三方名称
     */
    private String thirdName;

    /**
     * 第三方来源
     */
    private String source;

    /**
     * 第三方 id
     */
    private String clientId;

    /**
     * 第三方密钥
     */
    private String clientSecret;

    /**
     * 是否开启登录（1 是，2 否）
     */
    private Integer hasOpenLogin;

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
