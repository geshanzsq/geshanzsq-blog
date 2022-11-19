package com.geshanzsq.client.blog.footprint.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.footprint.mapper.BlogArticleFootprintMapper;
import com.geshanzsq.client.blog.footprint.service.BlogArticleFootprintService;
import com.geshanzsq.client.common.blog.enums.article.BlogArticleStatus;
import com.geshanzsq.client.common.blog.po.footprint.BlogArticleFootprint;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.util.PageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 文章足迹
 *
 * @author geshanzsq
 * @date 2022/10/14
 */
@Service
public class BlogArticleFootprintServiceImpl extends BaseServiceImpl<BlogArticleFootprintMapper, BlogArticleFootprint> implements BlogArticleFootprintService {

    @Autowired
    private BlogArticleFootprintMapper blogArticleFootprintMapper;

    /**
     * 保存足迹
     *
     * @param userId 用户 id
     * @param articleId 文章 id
     */
    @Override
    public void save(Long userId, Long articleId) {
        // 获取足迹信息，如果足迹不存在，则新增足迹，否则更新足迹时间
        LambdaQueryWrapper<BlogArticleFootprint> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogArticleFootprint::getCreateUserId, userId);
        wrapper.eq(BlogArticleFootprint::getArticleId, articleId);
        List<BlogArticleFootprint> list = blogArticleFootprintMapper.selectList(wrapper);
        BlogArticleFootprint blogArticleFootprint = CollectionUtils.isEmpty(list) ? null : list.get(0);

        // 新增
        if (blogArticleFootprint == null) {
            blogArticleFootprint = new BlogArticleFootprint();
            blogArticleFootprint.setCreateUserId(userId);
            blogArticleFootprint.setArticleId(articleId);
            // 设置修改时间，方便后续排序查询
            blogArticleFootprint.setGmtModify(new Date());
            blogArticleFootprintMapper.insert(blogArticleFootprint);
            return;
        }

        // 修改时间
        BlogArticleFootprint blogArticleFootprintUpdate = new BlogArticleFootprint();
        blogArticleFootprintUpdate.setId(blogArticleFootprint.getId());
        blogArticleFootprintUpdate.setGmtModify(new Date());
        blogArticleFootprintMapper.updateById(blogArticleFootprintUpdate);

    }

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogArticlePageVO> pageList(PageDTO pageDTO) {
        PageUtils.startPage(pageDTO);
        List<BlogArticlePageVO> list = blogArticleFootprintMapper.selectPage(BlogArticleStatus.PUBLISH.code, SecurityUtils.getUserId());
        return PageUtils.getPage(list);
    }
}
