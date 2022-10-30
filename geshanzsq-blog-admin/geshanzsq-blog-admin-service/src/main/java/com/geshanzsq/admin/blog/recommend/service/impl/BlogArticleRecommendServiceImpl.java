package com.geshanzsq.admin.blog.recommend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.admin.blog.recommend.mapstruct.BlogArticleRecommendConverter;
import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendListDTO;
import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendUpdateSortDTO;
import com.geshanzsq.admin.blog.recommend.mapper.BlogArticleRecommendMapper;
import com.geshanzsq.admin.blog.recommend.vo.BlogArticleRecommendVO;
import com.geshanzsq.client.common.blog.po.article.BlogArticleRecommend;
import com.geshanzsq.admin.blog.recommend.service.BlogArticleRecommendService;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章推荐
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
@Service
public class BlogArticleRecommendServiceImpl extends BaseServiceImpl<BlogArticleRecommendMapper, BlogArticleRecommend> implements BlogArticleRecommendService {

    @Autowired
    private BlogArticleRecommendMapper blogArticleRecommendMapper;

    /**
     * 删除推荐
     */
    @Override
    public void removeByArticleIds(List<Long> articleIds) {
        if (CollectionUtils.isEmpty(articleIds)) {
            return;
        }
        LambdaQueryWrapper<BlogArticleRecommend> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BlogArticleRecommend::getArticleId, articleIds);
        blogArticleRecommendMapper.delete(wrapper);
    }

    /**
     * 保存
     *
     * @param articleId 文章 id
     * @param recommendLevels 推荐等级
     */
    @Override
    public void save(Long articleId, List<Integer> recommendLevels) {
        if (CollectionUtils.isEmpty(recommendLevels)) {
            return;
        }
        List<BlogArticleRecommend> list = new ArrayList<>();
        recommendLevels.forEach(recommendLevel -> {
            BlogArticleRecommend blogArticleRecommend = new BlogArticleRecommend();
            blogArticleRecommend.setArticleId(articleId);
            blogArticleRecommend.setRecommendLevel(recommendLevel);
            list.add(blogArticleRecommend);
        });
        super.saveBatch(list);
    }

    /**
     * 更新
     *
     * @param articleId 文章 id
     * @param recommendLevels 推荐等级
     */
    @Override
    public void update(Long articleId, List<Integer> recommendLevels) {
        // 1 删除文章推荐
        this.removeByArticleIds(Arrays.asList(articleId));

        // 2 新增文章推荐
        this.save(articleId, recommendLevels);
    }


    /**
     * 获取文章推荐等级
     *
     * @param articleId 文章 id
     * @return
     */
    @Override
    public List<Integer> getRecommendLevel(Long articleId) {
        LambdaQueryWrapper<BlogArticleRecommend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogArticleRecommend::getArticleId, articleId);
        wrapper.select(BlogArticleRecommend::getRecommendLevel);
        List<BlogArticleRecommend> list = blogArticleRecommendMapper.selectList(wrapper);
        return list.stream().map(r -> r.getRecommendLevel()).collect(Collectors.toList());
    }

    /**
     * 列表
     * @return
     */
    @Override
    public List<BlogArticleRecommendVO> list(BlogArticleRecommendListDTO listDTO) {
        return blogArticleRecommendMapper.selectRecommendList(listDTO);
    }

    /**
     * 更新排序
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSort(List<BlogArticleRecommendUpdateSortDTO> sortDTOList) {
        if (CollectionUtils.isEmpty(sortDTOList)) {
            return;
        }
        for (BlogArticleRecommendUpdateSortDTO sortDTO : sortDTOList) {
            BlogArticleRecommend recommend = BlogArticleRecommendConverter.INSTANCE.convert(sortDTO);
            blogArticleRecommendMapper.updateById(recommend);
        }
    }
}
