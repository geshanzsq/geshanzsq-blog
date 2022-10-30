package com.geshanzsq.admin.blog.tag.service.impl;

import com.geshanzsq.admin.blog.article.service.BlogArticleTagService;
import com.geshanzsq.admin.blog.tag.dto.BlogTagListDTO;
import com.geshanzsq.admin.blog.tag.dto.BlogTagPageDTO;
import com.geshanzsq.admin.blog.tag.mapper.BlogTagMapper;
import com.geshanzsq.admin.blog.tag.mapstruct.BlogTagConverter;
import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import com.geshanzsq.admin.blog.tag.service.BlogTagService;
import com.geshanzsq.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 博客标签
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Service
public class BlogTagServiceImpl extends BaseServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogArticleTagService blogArticleTagService;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogTagVO> pageList(BlogTagPageDTO pageDTO) {
        PageVO<BlogTag> pageVO = blogTagMapper.selectPage(pageDTO);
        return BlogTagConverter.INSTANCE.convert(pageVO);
    }

    /**
     * 列表
     */
    @Override
    public List<BlogTag> list(BlogTagListDTO listDTO) {
        LambdaQueryWrapperPlus<BlogTag> wrapper = new LambdaQueryWrapperPlus<>();
        wrapper.eqIf(BlogTag::getStatus, listDTO.getStatus());
        wrapper.orderByAsc(BlogTag::getSort);
        return blogTagMapper.selectList(wrapper);
    }

    /**
     * 删除
     */
    @Override
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        List<Long> idList = Arrays.asList(ids);

        // 判断标签是否关联文章，如果已关联，则不能删除
        if (blogArticleTagService.isAssociateArticle(idList)) {
            throw new ParamException(MessageUtils.message("blog.tag.allocated.article"));
        }

        blogTagMapper.deleteBatchIds(idList);
    }

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return blogTagMapper.selectMaxSort();
    }

    /**
     * 获取标签
     * @param articleIds 文章 id 集合
     */
    @Override
    public List<BlogTagVO> getTagByArticleIds(List<Long> articleIds) {
        if (CollectionUtils.isEmpty(articleIds)) {
            return new ArrayList<>();
        }
        return blogTagMapper.selectListByArticleIds(articleIds);
    }
}
