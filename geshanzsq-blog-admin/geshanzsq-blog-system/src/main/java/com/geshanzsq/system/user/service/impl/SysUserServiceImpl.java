package com.geshanzsq.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.common.core.enums.DelFlag;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.framework.security.constant.SecurityConstant;
import com.geshanzsq.framework.security.domain.LoginUserDetail;
import com.geshanzsq.framework.security.utils.SecurityUtils;
import com.geshanzsq.system.api.mapstruct.SysApiConverter;
import com.geshanzsq.system.api.service.SysApiService;
import com.geshanzsq.system.api.vo.SysApiVO;
import com.geshanzsq.system.menu.service.SysMenuService;
import com.geshanzsq.system.menu.vo.SysMenuVO;
import com.geshanzsq.system.role.service.SysRoleService;
import com.geshanzsq.system.role.vo.SysRoleVO;
import com.geshanzsq.system.user.dto.SysUserAddDTO;
import com.geshanzsq.system.user.dto.SysUserPageDTO;
import com.geshanzsq.system.user.dto.SysUserResetPasswordDTO;
import com.geshanzsq.system.user.mapper.SysUserMapper;
import com.geshanzsq.system.user.mapstruct.SysUserConverter;
import com.geshanzsq.system.user.po.SysUser;
import com.geshanzsq.system.user.service.SysUserService;
import com.geshanzsq.system.user.vo.SysUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统用户
 *
 * @author geshanzsq
 * @date 2022/3/20
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysApiService sysApiService;

    /**
     * 获取登录用户
     * @param username 用户名
     */
    @Override
    public LoginUserDetail getLoginUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (sysUser == null) {
            log.info("用户：{}，不存在", username);
            return null;
        }
        LoginUserDetail loginUserDetail = SysUserConverter.INSTANCE.convertDetail(sysUser);
        return loginUserDetail;
    }

    /**
     * 设置登录成功后的用户权限
     */
    @Override
    public void setLoginUserPermission(LoginUserDetail loginUser) {
        // 1 获取角色
        List<SysRoleVO> roles = sysRoleService.getRoleByUserId(loginUser.getUserId());
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }

        // 角色 id
        Set<Long> roleIds = new HashSet<>();
        // 角色编码
        Set<String> roleCodes = new HashSet<>();
        // 获取角色 id 和角色编码
        for (SysRoleVO role: roles) {
            roleIds.add(role.getId());
            roleCodes.add(role.getRoleCode());
            // 是否为超级管理员
            if (SecurityConstant.SUPER_ADMIN_ROLE_CODE.equals(role.getRoleCode())) {
                loginUser.setHasSuperAdmin(true);
                Set<String> permissionCodes = new HashSet<>();
                permissionCodes.add(SecurityConstant.ALL_PERMISSION_CODE);
                loginUser.setPermissionCodes(permissionCodes);
            }
        }
        loginUser.setRoleCodes(roleCodes);

        // 2 如果为超级管理员，不用获取权限标识和接口地址
        if (loginUser.isHasSuperAdmin()) {
            return;
        }

        // 3 获取菜单权限和接口地址
        List<SysMenuVO> menus = sysMenuService.getMenuByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(menus)) {
            return;
        }

        // 4 设置权限标识
        Set<String> permissionCodes = menus.stream()
                .filter(m -> StrUtils.isNotBlank(m.getPermissionCode()))
                .map(m -> m.getPermissionCode())
                .collect(Collectors.toSet());
        loginUser.setPermissionCodes(permissionCodes);

        // 5 设置接口地址
        List<Long> menuIds = menus.stream().map(m -> m.getId()).collect(Collectors.toList());
        List<SysApiVO> apis = sysApiService.getApiByMenuIds(menuIds);
        loginUser.setApiPermissions(SysApiConverter.INSTANCE.convertList(apis));

    }

    /**
     * 通过用户 ids 获取用户名和昵称
     * @param userIds 用户 ids
     */
    @Override
    public List<SysUserVO> getUsernameAndNickNameByUserIds(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysUser::getId, new HashSet<>(userIds));
        wrapper.select(SysUser::getId, SysUser::getUsername, SysUser::getNickName);
        List<SysUser> list = sysUserMapper.selectList(wrapper);
        return SysUserConverter.INSTANCE.convertList(list);
    }

    /**
     * 分页列表
     */
    @Override
    public PageVO<SysUserVO> pageList(SysUserPageDTO pageDTO) {

        PageVO<SysUserVO> page = SysUserConverter.INSTANCE.convert(sysUserMapper.selectPage(pageDTO));
        List<SysUserVO> userList = page.getList();
        if (CollectionUtils.isEmpty(userList)) {
            return page;
        }

        // 获取用户角色
        List<Long> userIds = userList.stream().map(u -> u.getId()).collect(Collectors.toList());
        List<SysRoleVO> roles = sysRoleService.getRoleByUserIds(userIds);
        if (CollectionUtils.isEmpty(roles)) {
            return page;
        }

        // 设置用户角色名称
        for (SysUserVO user : userList) {
            for (SysRoleVO role : roles) {
                if (user.getId().equals(role.getUserId())) {
                    if (user.getRoleNames() == null) {
                        user.setRoleNames(new HashSet<>());
                    }
                    user.getRoleNames().add(role.getRoleName());
                }
            }
        }

        return page;
    }

    /**
     * 新增用户
     */
    @Override
    public void add(SysUserAddDTO addDTO) {
        // 判断用户名是否存在
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, addDTO.getUsername());
        Long count = sysUserMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ParamException(MessageUtils.message("user.username.exist"));
        }

        SysUser sysUser = SysUserConverter.INSTANCE.convert(addDTO);
        sysUser.setPassword(SecurityUtils.encryptPassword(addDTO.getPassword()));
        sysUserMapper.insert(sysUser);

    }

    /**
     * 重置密码
     */
    @Override
    public void resetPassword(SysUserResetPasswordDTO sysUserResetPasswordDto) {
        SysUser sysUser = SysUserConverter.INSTANCE.convert(sysUserResetPasswordDto);
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUserResetPasswordDto.getPassword()));
        sysUserMapper.updateById(sysUser);
    }

    /**
     * 过滤不存在的用户 ids
     * @param ids 用户 id 集合
     */
    @Override
    public List<Long> filterNotExistUserIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysUser::getId, ids);
        wrapper.eq(SysUser::getStatus, DelFlag.NO_DELETE.code);
        wrapper.select(SysUser::getId);
        List<SysUser> users = sysUserMapper.selectList(wrapper);
        return users.stream().map(SysUser::getId).collect(Collectors.toList());
    }
}
