package com.geshanzsq.client.blog.like.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.article.service.BlogArticleService;
import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.like.dto.BlogArticleLikeAddDTO;
import com.geshanzsq.client.blog.like.mapper.BlogArticleLikeMapper;
import com.geshanzsq.client.blog.like.service.BlogArticleLikeService;
import com.geshanzsq.client.blog.like.vo.BlogArticleLikeCountVO;
import com.geshanzsq.client.common.blog.enums.article.BlogArticleStatus;
import com.geshanzsq.client.common.blog.po.like.BlogArticleLike;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.util.PageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章点赞
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Service
public class BlogArticleLikeServiceImpl extends BaseServiceImpl<BlogArticleLikeMapper, BlogArticleLike> implements BlogArticleLikeService {

    @Autowired
    private BlogArticleLikeMapper blogArticleLikeMapper;
    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 获取点赞数量
     */
    @Override
    public List<BlogArticleLikeCountVO> getCountByArticleIds(List<Long> articleIds) {
        if (CollectionUtils.isEmpty(articleIds)) {
            return new ArrayList<>();
        }
        return blogArticleLikeMapper.selectCountByArticleIds(articleIds);
    }

    /**
     * 新增
     */
    @Override
    public void save(BlogArticleLikeAddDTO addDTO) {
        // 1、校验文章是否存在
        blogArticleService.checkArticleExist(addDTO.getArticleId());

        // 2、判断是否点赞过
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<BlogArticleLike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogArticleLike::getArticleId, addDTO.getArticleId());
        wrapper.eq(BlogArticleLike::getCreateUserId, userId);
        if (blogArticleLikeMapper.selectCount(wrapper) > 0) {
            throw new ParamException(MessageUtils.message("article.like.exist"));
        }

        // 3、新增点赞
        BlogArticleLike blogArticleLike = new BlogArticleLike();
        blogArticleLike.setArticleId(addDTO.getArticleId());
        blogArticleLike.setCreateUserId(userId);
        blogArticleLikeMapper.insert(blogArticleLike);

    }

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogArticlePageVO> pageList(PageDTO pageDTO) {
        PageUtils.startPage(pageDTO);
        List<BlogArticlePageVO> list = blogArticleLikeMapper.selectList(BlogArticleStatus.PUBLISH.code, SecurityUtils.getUserId());
        return PageUtils.getPage(list);
    }
}
