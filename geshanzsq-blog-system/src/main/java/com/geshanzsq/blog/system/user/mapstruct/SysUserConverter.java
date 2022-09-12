package com.geshanzsq.blog.system.user.mapstruct;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.framework.security.domain.LoginUserDetail;
import com.geshanzsq.blog.system.user.dto.SysUserAddDTO;
import com.geshanzsq.blog.system.user.dto.SysUserResetPasswordDTO;
import com.geshanzsq.blog.system.user.dto.SysUserUpdateDTO;
import com.geshanzsq.blog.system.user.po.SysUser;
import com.geshanzsq.blog.system.user.vo.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户对象转换
 *
 * @author geshanzsq
 * @date 2022/3/27
 */
@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    @Mapping(source = "id", target = "userId")
    LoginUserDetail convertDetail(SysUser sysUser);

    SysUserVO convert(SysUser sysUser);

    List<SysUserVO> convertList(List<SysUser> list);

    PageVO<SysUserVO> convert(PageVO<SysUser> pageVo);

    SysUser convert(SysUserAddDTO addDTO);

    SysUser convert(SysUserUpdateDTO updateDTO);

    SysUser convert(SysUserResetPasswordDTO sysUserResetPasswordDTO);

}
