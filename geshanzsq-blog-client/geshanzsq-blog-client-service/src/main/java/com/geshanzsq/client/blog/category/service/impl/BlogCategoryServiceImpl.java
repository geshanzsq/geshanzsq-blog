package com.geshanzsq.client.blog.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.article.service.BlogArticleService;
import com.geshanzsq.client.blog.article.vo.BlogArticleCategoryCountVO;
import com.geshanzsq.client.blog.category.mapper.BlogCategoryMapper;
import com.geshanzsq.client.blog.category.mapstruct.BlogCategoryConverter;
import com.geshanzsq.client.blog.category.service.BlogCategoryService;
import com.geshanzsq.client.blog.category.vo.BlogCategoryListVO;
import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.common.core.enums.CommonStatus;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 分类
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Service
public class BlogCategoryServiceImpl extends BaseServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 列表
     */
    @Override
    public List<BlogCategoryListVO> listCategory() {
        LambdaQueryWrapper<BlogCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogCategory::getStatus, CommonStatus.NORMAL.code);
        wrapper.select(BlogCategory::getId, BlogCategory::getCategoryName);
        wrapper.orderByAsc(BlogCategory::getSort);
        List<BlogCategoryListVO> list = BlogCategoryConverter.INSTANCE.convert(blogCategoryMapper.selectList(wrapper));
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }

        // 获取分类对应的文章数
        List<BlogArticleCategoryCountVO> articleCategoryCountList = blogArticleService.getArticleCategoryCount();
        list.forEach(category -> {
            BlogArticleCategoryCountVO articleCategoryCountVO = articleCategoryCountList.stream()
                    .filter(ac -> category.getId().equals(ac.getCategoryId())).findFirst().orElse(null);
            Long articleCount = articleCategoryCountVO != null ? articleCategoryCountVO.getArticleCount() : 0L;
            category.setArticleCount(articleCount);
        });
        return list;
    }

    /**
     * 更新点击量
     * @param categoryName 分类名称
     */
    @Override
    public void updateClickCountByCategoryName(String categoryName) {
        if (StrUtils.isBlank(categoryName)) {
            return;
        }
        blogCategoryMapper.updateClickCountByCategoryName(categoryName, CommonStatus.NORMAL.code);
    }
}
