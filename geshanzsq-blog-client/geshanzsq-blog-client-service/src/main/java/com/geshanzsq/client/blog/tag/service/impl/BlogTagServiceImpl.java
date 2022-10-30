package com.geshanzsq.client.blog.tag.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.tag.mapper.BlogTagMapper;
import com.geshanzsq.client.blog.tag.mapstruct.BlogTagConverter;
import com.geshanzsq.client.blog.tag.service.BlogTagService;
import com.geshanzsq.client.blog.tag.vo.BlogTagListVO;
import com.geshanzsq.client.common.blog.po.tag.BlogTag;
import com.geshanzsq.common.core.enums.CommonStatus;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Service
public class BlogTagServiceImpl extends BaseServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    /**
     * 列表
     */
    @Override
    public List<BlogTagListVO> listTag() {
        LambdaQueryWrapper<BlogTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogTag::getStatus, CommonStatus.NORMAL.code);
        wrapper.select(BlogTag::getTagName);
        wrapper.orderByAsc(BlogTag::getSort);
        List<BlogTag> list = blogTagMapper.selectList(wrapper);
        return BlogTagConverter.INSTANCE.convert(list);
    }

    /**
     * 更新点击量
     * @param tagName 标签名称
     */
    @Override
    public void updateClickCountByTagName(String tagName) {
        if (StrUtils.isBlank(tagName)) {
            return;
        }
        blogTagMapper.updateClickCountByTagName(tagName, CommonStatus.NORMAL.code);
    }

    /**
     * 通过文章 id 获取标签名称
     *
     * @param articleId 文章 id
     */
    @Override
    public List<String> getTagNameByArticleId(Long articleId) {
        return blogTagMapper.selectTagNameByArticleId(articleId);
    }
}
