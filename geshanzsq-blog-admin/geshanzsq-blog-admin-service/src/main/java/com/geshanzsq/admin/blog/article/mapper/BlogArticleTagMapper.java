package com.geshanzsq.admin.blog.article.mapper;

import com.geshanzsq.admin.blog.article.po.BlogArticleTag;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章标签
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Mapper
public interface BlogArticleTagMapper extends BaseMapperPlus<BlogArticleTag> {

    /**
     * 获取关联文章数量
     *
     * @param tagIds 标签 id 集合
     * @return
     */
    Long selectAssociateArticleCount(@Param("tagIds") List<Long> tagIds);
}
