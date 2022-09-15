package com.geshanzsq.admin.blog.category.mapstruct;

import com.geshanzsq.admin.blog.category.dto.BlogCategoryAddDTO;
import com.geshanzsq.admin.blog.category.dto.BlogCategoryUpdateDTO;
import com.geshanzsq.admin.blog.category.po.BlogCategory;
import com.geshanzsq.admin.blog.category.vo.BlogCategoryVO;
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
public interface BlogCategoryConverter {

    BlogCategoryConverter INSTANCE = Mappers.getMapper(BlogCategoryConverter.class);

    PageVO<BlogCategoryVO> convert(PageVO<BlogCategory> pageVO);

    List<BlogCategoryVO> convertList(List<BlogCategory> list);

    BlogCategoryVO convert(BlogCategory blogCategory);

    BlogCategory convert(BlogCategoryAddDTO addDTO);

    BlogCategory convert(BlogCategoryUpdateDTO updateDTO);

}
