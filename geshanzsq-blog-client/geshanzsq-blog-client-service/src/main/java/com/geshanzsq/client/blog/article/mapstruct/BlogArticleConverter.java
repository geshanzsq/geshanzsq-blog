package com.geshanzsq.client.blog.article.mapstruct;

import com.geshanzsq.client.blog.article.vo.BlogArticleVO;
import com.geshanzsq.client.common.blog.po.article.BlogArticle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 文章对象转换
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
@Mapper
public interface BlogArticleConverter {

    BlogArticleConverter INSTANCE = Mappers.getMapper(BlogArticleConverter.class);

    BlogArticleVO convert(BlogArticle blogArticle);

}
