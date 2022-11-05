package com.geshanzsq.admin.blog.tag.mapper;

import com.geshanzsq.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客标签
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Mapper
public interface BlogTagMapper extends BaseMapperPlus<BlogTag> {

    /**
     * 获取最大排序
     */
    Integer selectMaxSort();

    /**
     * 获取标签
     * @param articleIds 文章 id 集合
     * @return
     */
    List<BlogTagVO> selectListByArticleIds(@Param("articleIds") List<Long> articleIds);
}
