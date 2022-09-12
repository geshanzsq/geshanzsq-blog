package com.geshanzsq.blog.system.role.mapstruct;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.system.role.dto.SysRoleAddDTO;
import com.geshanzsq.blog.system.role.dto.SysRoleAuthUserPageDTO;
import com.geshanzsq.blog.system.role.dto.SysRoleNotAuthUserPageDTO;
import com.geshanzsq.blog.system.role.dto.SysRoleUpdateDTO;
import com.geshanzsq.blog.system.role.po.SysRole;
import com.geshanzsq.blog.system.role.vo.SysRoleVO;
import com.geshanzsq.blog.system.user.dto.SysUserPageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色对象转换
 *
 * @author geshanzsq
 * @date 2022/6/19
 */
@Mapper
public interface SysRoleConverter {

    SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);

    SysRoleVO convert(SysRole sysRole);

    PageVO<SysRoleVO> convert(PageVO<SysRole> pageVO);

    SysRole convert(SysRoleAddDTO addDTO);

    SysRole convert(SysRoleUpdateDTO updateDTO);

    SysUserPageDTO convert(SysRoleAuthUserPageDTO pageDTO);

    SysUserPageDTO convert(SysRoleNotAuthUserPageDTO pageDTO);

}
