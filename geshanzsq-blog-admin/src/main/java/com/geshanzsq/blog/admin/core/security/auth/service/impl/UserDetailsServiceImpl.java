package com.geshanzsq.blog.admin.core.security.auth.service.impl;

import com.geshanzsq.blog.common.core.exception.ServiceException;
import com.geshanzsq.blog.common.core.util.message.MessageUtils;
import com.geshanzsq.blog.framework.security.domain.LoginUserDetail;
import com.geshanzsq.blog.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author geshanzsq
 * @date 2022/3/24
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取登录用户
        LoginUserDetail loginUserDetail = sysUserService.getLoginUserByUsername(username);
        if (loginUserDetail == null) {
            throw new ServiceException(MessageUtils.message("login.username.password.not.match"));
        }
        return loginUserDetail;
    }
}
