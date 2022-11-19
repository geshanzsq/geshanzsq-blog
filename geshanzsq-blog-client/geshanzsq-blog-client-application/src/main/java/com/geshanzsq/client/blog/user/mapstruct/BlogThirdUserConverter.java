package com.geshanzsq.client.blog.user.mapstruct;

import com.geshanzsq.client.common.blog.po.user.BlogThirdUser;
import com.geshanzsq.client.core.security.auth.vo.ThirdLoginAuthResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 博客第三方用户对象转换
 * 
 * @author geshanzsq
 * @date 2022/10/7
 */
@Mapper
public interface BlogThirdUserConverter {

    BlogThirdUserConverter INSTANCE = Mappers.getMapper(BlogThirdUserConverter.class);

    BlogThirdUser convert(ThirdLoginAuthResponseVO authResponseVO);
    
}
