package com.geshanzsq.blog.admin.blog.tag.service;

import com.geshanzsq.blog.admin.blog.tag.dto.BlogTagListDTO;
import com.geshanzsq.blog.admin.blog.tag.dto.BlogTagPageDTO;
import com.geshanzsq.blog.admin.blog.tag.po.BlogTag;
import com.geshanzsq.blog.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 博客标签
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public interface BlogTagService extends BaseService<BlogTag> {

    /**
     * 分页列表
     */
    PageVO<BlogTagVO> pageList(BlogTagPageDTO pageDTO);

    /**
     * 列表
     */
    List<BlogTag> list(BlogTagListDTO listDTO);

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
