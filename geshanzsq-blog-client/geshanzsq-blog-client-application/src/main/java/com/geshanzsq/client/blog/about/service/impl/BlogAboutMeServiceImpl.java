package com.geshanzsq.client.blog.about.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.about.mapper.BlogAboutMeMapper;
import com.geshanzsq.client.blog.about.service.BlogAboutMeService;
import com.geshanzsq.client.blog.about.vo.BlogAboutMeVO;
import com.geshanzsq.client.common.blog.enums.article.EditorType;
import com.geshanzsq.client.common.blog.po.about.BlogAboutMe;
import com.geshanzsq.client.common.blog.util.MarkdownUtils;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 关于我
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Service
public class BlogAboutMeServiceImpl extends BaseServiceImpl<BlogAboutMeMapper, BlogAboutMe> implements BlogAboutMeService {

    @Autowired
    private BlogAboutMeMapper blogAboutMeMapper;

    /**
     * 获取内容
     */
    @Override
    public BlogAboutMeVO getContent() {
        BlogAboutMeVO blogAboutMeVO = new BlogAboutMeVO();

        LambdaQueryWrapper<BlogAboutMe> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(BlogAboutMe::getContent, BlogAboutMe::getEditorType);
        wrapper.last(" limit 1");
        BlogAboutMe blogAboutMe = blogAboutMeMapper.selectOne(wrapper);
        if (blogAboutMe == null) {
            return blogAboutMeVO;
        }

        // 渲染 Markdown 内容
        String content = blogAboutMe.getContent();
        if (EditorType.MARKDOWN.code.equals(blogAboutMe.getEditorType())) {
            content = MarkdownUtils.markdownToHtml(content);
        }
        blogAboutMeVO.setContent(content);

        return blogAboutMeVO;
    }
}
