package com.geshanzsq.admin.blog.recommend.mapstruct;

import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendUpdateSortDTO;
import com.geshanzsq.client.common.blog.po.article.BlogArticleRecommend;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 文章推荐对象转换
 *
 * @author geshanzsq
 * @date 2022/10/29
 */
@Mapper
public interface BlogArticleRecommendConverter {

    BlogArticleRecommendConverter INSTANCE = Mappers.getMapper(BlogArticleRecommendConverter.class);

    BlogArticleRecommend convert(BlogArticleRecommendUpdateSortDTO sortDTO);

}
