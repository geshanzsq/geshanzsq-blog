package com.geshanzsq.admin.blog.recommend.mapper;

import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendListDTO;
import com.geshanzsq.admin.blog.recommend.vo.BlogArticleRecommendVO;
import com.geshanzsq.client.common.blog.po.article.BlogArticleRecommend;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章推荐
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Mapper
public interface BlogArticleRecommendMapper extends BaseMapperPlus<BlogArticleRecommend> {

    /**
     * 列表
     */
    List<BlogArticleRecommendVO> selectRecommendList(BlogArticleRecommendListDTO listDTO);
}
