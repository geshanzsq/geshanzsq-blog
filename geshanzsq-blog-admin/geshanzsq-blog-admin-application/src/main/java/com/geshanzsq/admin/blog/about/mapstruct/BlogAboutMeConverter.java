package com.geshanzsq.admin.blog.about.mapstruct;

import com.geshanzsq.admin.blog.about.BlogAboutMeEditDTO;
import com.geshanzsq.admin.blog.about.vo.BlogAboutMeVO;
import com.geshanzsq.client.common.blog.po.about.BlogAboutMe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 关于我对象转换
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Mapper
public interface BlogAboutMeConverter {


    BlogAboutMeConverter INSTANCE = Mappers.getMapper(BlogAboutMeConverter.class);

    BlogAboutMeVO convert(BlogAboutMe blogAboutMe);

    BlogAboutMe convert(BlogAboutMeEditDTO editDTO);

}
