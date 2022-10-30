package com.geshanzsq.client.blog.tag.mapper;

import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Mapper
public interface BlogTagMapper extends BaseMapperPlus<BlogTag> {

    /**
     * 更新点击量
     * @param tagName 标签名
     * @param status 状态
     */
    void updateClickCountByTagName(@Param("tagName") String tagName,
                                   @Param("status") Integer status);

    /**
     * 通过文章 id 获取标签名称
     * @param articleId 文章 id
     * @return
     */
    List<String> selectTagNameByArticleId(@Param("articleId") Long articleId);
}
