package com.geshanzsq.admin.blog.recommend.service;

import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendListDTO;
import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendUpdateSortDTO;
import com.geshanzsq.admin.blog.recommend.vo.BlogArticleRecommendVO;
import com.geshanzsq.client.common.blog.po.article.BlogArticleRecommend;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 文章推荐
 *
 * @author geshanzsq
 * @date 2022/8/20
 */
public interface BlogArticleRecommendService extends BaseService<BlogArticleRecommend> {

    /**
     * 删除推荐
     */
    void removeByArticleIds(List<Long> articleIds);

    /**
     * 保存
     *
     * @param articleId 文章 id
     * @param recommendLevels 推荐等级
     */
    void save(Long articleId, List<Integer> recommendLevels);


    /**
     * 更新
     *
     * @param articleId 文章 id
     * @param recommendLevels 推荐等级
     */
    void update(Long articleId, List<Integer> recommendLevels);

    /**
     * 获取文章推荐等级
     *
     * @param articleId 文章 id
     * @return
     */
    List<Integer> getRecommendLevel(Long articleId);

    /**
     * 列表
     * @return
     */
    List<BlogArticleRecommendVO> list(BlogArticleRecommendListDTO listDTO);

    /**
     * 更新排序
     */
    void updateSort(List<BlogArticleRecommendUpdateSortDTO> sortDTOList);
}
