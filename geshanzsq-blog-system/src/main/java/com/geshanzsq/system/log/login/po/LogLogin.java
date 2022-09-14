package com.geshanzsq.system.log.login.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @author geshanzsq
 * @date 2022/7/3
 */
@Data
public class LogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录日志 id
     */
    @TableId
    private Long id;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录 ip 地址
     */
    private String ipAddress;

    /**
     * 登录位置
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browserType;

    /**
     * 操作系统
     */
    private String operateSystem;

    /**
     * 登录状态（1 成功，2 失败）
     */
    private Integer status;

    /**
     * 提示消息
     */
    private String hintMessage;

    /**
     * 登录时间
     */
    private Date gmtLogin;
}
