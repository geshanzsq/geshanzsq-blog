package com.geshanzsq.blog.system.log.login.factory;

import com.geshanzsq.blog.common.core.util.ip.AddressUtils;
import com.geshanzsq.blog.common.core.util.ip.IpUtils;
import com.geshanzsq.blog.common.core.util.servlet.ServletUtils;
import com.geshanzsq.blog.common.core.util.spring.SpringUtils;
import com.geshanzsq.blog.system.log.login.po.LogLogin;
import com.geshanzsq.blog.system.log.login.service.LogLoginService;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.Date;
import java.util.TimerTask;

/**
 * 登录日志异步工厂
 *
 * @author geshanzsq
 * @date 2022/7/4
 */
public class LogLoginAsyncFactory {

    /**
     * 记录日志
     * @param username 用户名
     * @param status 状态
     * @param hintMessage 提示消息
     */
    public static TimerTask add(String username, Integer status, String hintMessage) {
        // 获取客户端信息
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                // 获取客户端操作系统
                String operateSystem = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                LogLogin logLogin = new LogLogin();
                logLogin.setUsername(username);
                logLogin.setIpAddress(ip);
                logLogin.setLoginLocation(address);
                logLogin.setBrowserType(browser);
                logLogin.setOperateSystem(operateSystem);
                logLogin.setHintMessage(hintMessage);
                logLogin.setStatus(status);
                logLogin.setGmtLogin(new Date());
                SpringUtils.getBean(LogLoginService.class).save(logLogin);
            }
        };
    }

}
