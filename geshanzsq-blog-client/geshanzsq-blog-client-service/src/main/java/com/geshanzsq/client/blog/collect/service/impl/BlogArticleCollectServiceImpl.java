package com.geshanzsq.client.blog.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.article.service.BlogArticleService;
import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.collect.dto.BlogArticleCollectAddDTO;
import com.geshanzsq.client.blog.collect.mapper.BlogArticleCollectMapper;
import com.geshanzsq.client.blog.collect.service.BlogArticleCollectService;
import com.geshanzsq.client.blog.collect.vo.BlogArticleCollectCountVO;
import com.geshanzsq.client.common.blog.enums.article.BlogArticleStatus;
import com.geshanzsq.client.common.blog.po.collect.BlogArticleCollect;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.util.PageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章收藏
 *
 * @author geshanzsq
 * @date 2022/10/8
 */
@Service
public class BlogArticleCollectServiceImpl extends BaseServiceImpl<BlogArticleCollectMapper, BlogArticleCollect> implements BlogArticleCollectService {

    @Autowired
    private BlogArticleCollectMapper blogArticleCollectMapper;

    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 获取收藏数量
     */
    @Override
    public List<BlogArticleCollectCountVO> getCountByArticleIds(List<Long> articleIds) {
        if (CollectionUtils.isEmpty(articleIds)) {
            return new ArrayList<>();
        }
        return blogArticleCollectMapper.selectCountByArticleIds(articleIds);
    }

    /**
     * 新增
     */
    @Override
    public void save(BlogArticleCollectAddDTO addDTO) {
        // 1、校验文章是否存在
        blogArticleService.checkArticleExist(addDTO.getArticleId());

        // 2、判断是否点收藏过
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<BlogArticleCollect> wrapper = new LambdaQueryWrapperPlus<>();
        wrapper.eq(BlogArticleCollect::getArticleId, addDTO.getArticleId());
        wrapper.eq(BlogArticleCollect::getCreateUserId, userId);
        if (blogArticleCollectMapper.selectCount(wrapper) > 0) {
            throw new ParamException(MessageUtils.message("article.collect.exist"));
        }

        // 3、新增收藏
        BlogArticleCollect blogArticleCollect = new BlogArticleCollect();
        blogArticleCollect.setArticleId(addDTO.getArticleId());
        blogArticleCollect.setCreateUserId(userId);
        blogArticleCollectMapper.insert(blogArticleCollect);
    }

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogArticlePageVO> pageList(PageDTO pageDTO) {
        PageUtils.startPage(pageDTO);
        List<BlogArticlePageVO> list = blogArticleCollectMapper.selectList(BlogArticleStatus.PUBLISH.code, SecurityUtils.getUserId());
        return PageUtils.getPage(list);
    }
}
