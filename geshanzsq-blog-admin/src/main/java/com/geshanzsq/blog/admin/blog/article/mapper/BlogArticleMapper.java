package com.geshanzsq.blog.admin.blog.article.mapper;

import com.geshanzsq.blog.admin.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.blog.admin.blog.article.po.BlogArticle;
import com.geshanzsq.blog.admin.blog.article.vo.BlogArticleVO;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
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
