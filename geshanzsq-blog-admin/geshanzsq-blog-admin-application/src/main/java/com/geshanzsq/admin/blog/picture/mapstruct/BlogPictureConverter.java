package com.geshanzsq.admin.blog.picture.mapstruct;

import com.geshanzsq.admin.blog.picture.po.BlogPicture;
import com.geshanzsq.admin.blog.picture.vo.BlogPictureVO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 图片对象转换
 *
 * @author geshanzsq
 * @date 2022/9/3
 */
@Mapper
public interface BlogPictureConverter {

    BlogPictureConverter INSTANCE = Mappers.getMapper(BlogPictureConverter.class);

    PageVO<BlogPictureVO> convert(PageVO<BlogPicture> pageVO);

}
