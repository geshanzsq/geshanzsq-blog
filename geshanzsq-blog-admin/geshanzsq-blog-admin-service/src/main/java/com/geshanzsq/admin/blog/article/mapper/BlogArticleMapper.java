package com.geshanzsq.admin.blog.article.mapper;

import com.geshanzsq.admin.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.client.web.po.blog.article.BlogArticle;
import com.geshanzsq.admin.blog.article.vo.BlogArticleVO;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/8/18
 */
@Mapper
public interface BlogArticleMapper extends BaseMapperPlus<BlogArticle> {

    /**
     * 分页列表
     */
    List<BlogArticleVO> selectPage(BlogArticlePageDTO pageDTO);
}
