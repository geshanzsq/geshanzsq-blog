package com.geshanzsq.admin.blog.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.admin.blog.article.mapper.BlogArticleMapper;
import com.geshanzsq.admin.blog.article.po.BlogArticle;
import com.geshanzsq.admin.blog.category.dto.BlogCategoryListDTO;
import com.geshanzsq.admin.blog.category.dto.BlogCategoryPageDTO;
import com.geshanzsq.admin.blog.category.mapper.BlogCategoryMapper;
import com.geshanzsq.admin.blog.category.mapstruct.BlogCategoryConverter;
import com.geshanzsq.admin.blog.category.po.BlogCategory;
import com.geshanzsq.admin.blog.category.service.BlogCategoryService;
import com.geshanzsq.admin.blog.category.vo.BlogCategoryVO;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 博客分类
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Service
public class BlogCategoryServiceImpl extends BaseServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private BlogArticleMapper blogArticleMapper;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogCategoryVO> pageList(BlogCategoryPageDTO pageDTO) {
        PageVO<BlogCategory> pageVO = blogCategoryMapper.selectPage(pageDTO);
        return BlogCategoryConverter.INSTANCE.convert(pageVO);
    }

    /**
     * 列表
     * @return
     */
    @Override
    public List<BlogCategory> list(BlogCategoryListDTO listDTO) {
        LambdaQueryWrapperPlus<BlogCategory> wrapper = new LambdaQueryWrapperPlus<>();
        wrapper.eqIf(BlogCategory::getStatus, listDTO.getStatus());
        wrapper.orderByAsc(BlogCategory::getSort);
        return blogCategoryMapper.selectList(wrapper);
    }

    /**
     * 删除
     */
    @Override
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        // 判断分类是否已关联文章，如果已关联，则不能删除
        LambdaQueryWrapper<BlogArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BlogArticle::getCategoryId, ids);
        Long count = blogArticleMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ParamException(MessageUtils.message("blog.category.allocated.article"));
        }

        // 删除分类
        blogCategoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return blogCategoryMapper.getMaxSort();
    }
}
