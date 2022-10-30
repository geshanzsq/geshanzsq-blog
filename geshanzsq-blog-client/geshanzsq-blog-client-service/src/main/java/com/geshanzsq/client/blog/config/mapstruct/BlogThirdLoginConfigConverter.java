package com.geshanzsq.client.blog.config.mapstruct;

import com.geshanzsq.client.blog.config.vo.BlogThirdLoginConfigListVO;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 第三方登录配置对象转换
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Mapper
public interface BlogThirdLoginConfigConverter {

    BlogThirdLoginConfigConverter INSTANCE = Mappers.getMapper(BlogThirdLoginConfigConverter.class);

    List<BlogThirdLoginConfigListVO> convert(List<BlogThirdLoginConfig> list);

}
