package com.geshanzsq.admin.blog.tag.service;

import com.geshanzsq.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 博客标签
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public interface BlogTagService extends BaseService<BlogTag> {

    /**
     * 删除
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();

    /**
     * 获取标签
     * @param articleIds 文章 id 集合
     */
    List<BlogTagVO> getTagByArticleIds(List<Long> articleIds);
}
