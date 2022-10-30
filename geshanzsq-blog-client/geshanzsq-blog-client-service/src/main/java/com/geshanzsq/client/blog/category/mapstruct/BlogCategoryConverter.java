package com.geshanzsq.client.blog.category.mapstruct;

import com.geshanzsq.client.blog.category.vo.BlogCategoryListVO;
import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 分类对象转换
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Mapper
public interface BlogCategoryConverter {

    BlogCategoryConverter INSTANCE = Mappers.getMapper(BlogCategoryConverter.class);

    List<BlogCategoryListVO> convert(List<BlogCategory> list);

}
