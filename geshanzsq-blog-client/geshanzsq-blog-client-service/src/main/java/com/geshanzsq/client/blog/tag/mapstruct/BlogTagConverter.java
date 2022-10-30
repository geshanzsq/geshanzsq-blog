package com.geshanzsq.client.blog.tag.mapstruct;

import com.geshanzsq.client.blog.tag.vo.BlogTagListVO;
import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签对象转换
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Mapper
public interface BlogTagConverter {

    BlogTagConverter INSTANCE = Mappers.getMapper(BlogTagConverter.class);

    List<BlogTagListVO> convert(List<BlogTag> list);

}
