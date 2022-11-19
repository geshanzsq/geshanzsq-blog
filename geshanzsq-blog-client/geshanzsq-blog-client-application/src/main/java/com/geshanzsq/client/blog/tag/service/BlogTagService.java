package com.geshanzsq.client.blog.tag.service;

import com.geshanzsq.client.blog.tag.vo.BlogTagListVO;
import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 标签
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
public interface BlogTagService extends BaseService<BlogTag> {

    /**
     * 列表
     */
    List<BlogTagListVO> listTag();

    /**
     * 更新点击量
     *
     * @param tagName 标签名称
     */
    void updateClickCountByTagName(String tagName);

    /**
     * 通过文章 id 获取标签名称
     *
     * @param articleId 文章 id
     */
    List<String> getTagNameByArticleId(Long articleId);
}
