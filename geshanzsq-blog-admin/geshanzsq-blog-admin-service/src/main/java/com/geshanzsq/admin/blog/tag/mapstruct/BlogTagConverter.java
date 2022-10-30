package com.geshanzsq.admin.blog.tag.mapstruct;

import com.geshanzsq.admin.blog.tag.dto.BlogTagAddDTO;
import com.geshanzsq.admin.blog.tag.dto.BlogTagUpdateDTO;
import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import com.geshanzsq.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 博客分类对象转换
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Mapper
public interface BlogTagConverter {

    BlogTagConverter INSTANCE = Mappers.getMapper(BlogTagConverter.class);

    PageVO<BlogTagVO> convert(PageVO<BlogTag> pageVO);

    List<BlogTagVO> convertList(List<BlogTag> list);

    BlogTagVO convert(BlogTag blogCategory);

    BlogTag convert(BlogTagAddDTO addDTO);

    BlogTag convert(BlogTagUpdateDTO updateDTO);

}
