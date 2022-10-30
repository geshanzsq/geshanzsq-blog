package com.geshanzsq.system.user.service;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;
import com.geshanzsq.framework.security.domain.LoginUserDetail;
import com.geshanzsq.system.user.dto.SysUserAddDTO;
import com.geshanzsq.system.user.dto.SysUserPageDTO;
import com.geshanzsq.system.user.dto.SysUserResetPasswordDTO;
import com.geshanzsq.admin.system.common.po.user.SysUser;
import com.geshanzsq.system.user.vo.SysUserVO;

import java.util.List;

/**
 * 系统用户
 *
 * @author geshanzsq
 * @date 2022/3/20
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 获取登录用户
     * @param username 用户名
     */
    LoginUserDetail getLoginUserByUsername(String username);


    /**
     * 设置登录成功后的用户权限
     */
    void setLoginUserPermission(LoginUserDetail loginUser);

    /**
     * 通过用户 ids 获取用户名和昵称
     * @param userIds 用户 ids
     */
    List<SysUserVO> getUsernameAndNickNameByUserIds(List<Long> userIds);

    /**
     * 分页列表
     */
    PageVO<SysUserVO> pageList(SysUserPageDTO pageDTO);

    /**
     * 新增用户
     */
    void add(SysUserAddDTO addDTO);

    /**
     * 重置密码
     */
    void resetPassword(SysUserResetPasswordDTO sysUserResetPasswordDto);

    /**
     * 过滤不存在的用户 ids
     * @param ids 用户 id 集合
     */
    List<Long> filterNotExistUserIds(List<Long> ids);
}
