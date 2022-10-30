package com.geshanzsq.admin.blog.config.mapstruct;

import com.geshanzsq.admin.blog.config.dto.BlogThirdLoginConfigAddDTO;
import com.geshanzsq.admin.blog.config.dto.BlogThirdLoginConfigUpdateDTO;
import com.geshanzsq.admin.blog.config.vo.BlogThirdLoginConfigVO;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 第三方登录配置对象转换
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Mapper
public interface BlogThirdLoginConfigConverter {

    BlogThirdLoginConfigConverter INSTANCE = Mappers.getMapper(BlogThirdLoginConfigConverter.class);

    PageVO<BlogThirdLoginConfigVO> convert(PageVO<BlogThirdLoginConfig> pageVO);

    BlogThirdLoginConfigVO convert(BlogThirdLoginConfig blogThirdLoginConfig);

    BlogThirdLoginConfig convert(BlogThirdLoginConfigAddDTO addDTO);

    BlogThirdLoginConfig convert(BlogThirdLoginConfigUpdateDTO updateDTO);

}
