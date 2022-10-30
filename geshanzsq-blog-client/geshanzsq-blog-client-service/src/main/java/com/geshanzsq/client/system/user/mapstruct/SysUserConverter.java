package com.geshanzsq.client.system.user.mapstruct;

import com.geshanzsq.admin.system.common.po.user.SysUser;
import com.geshanzsq.client.blog.article.vo.BlogArticleAuthorVO;
import com.geshanzsq.client.core.security.auth.vo.ThirdLoginAuthResponseVO;
import com.geshanzsq.client.system.user.dto.SysUserUpdateDTO;
import com.geshanzsq.client.system.user.vo.SysUserVO;
import com.geshanzsq.framework.security.domain.LoginUserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户对象转换
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    BlogArticleAuthorVO convertAuthor(SysUser sysUser);

    @Mappings({
            @Mapping(source = "nickname", target = "nickName"),
            @Mapping(source = "mobile", target = "mobilePhone")
    })
    SysUser convert(ThirdLoginAuthResponseVO authResponseVO);

    @Mapping(source = "id", target = "userId")
    LoginUserDetail convertDetail(SysUser sysUser);

    SysUserVO convert(SysUser sysUser);

    SysUser convert(SysUserUpdateDTO userUpdateDTO);

}
