package com.geshanzsq.admin.blog.about.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.admin.blog.about.BlogAboutMeEditDTO;
import com.geshanzsq.admin.blog.about.mapper.BlogAboutMeMapper;
import com.geshanzsq.admin.blog.about.mapstruct.BlogAboutMeConverter;
import com.geshanzsq.admin.blog.about.service.BlogAboutMeService;
import com.geshanzsq.admin.blog.about.vo.BlogAboutMeVO;
import com.geshanzsq.client.common.blog.po.about.BlogAboutMe;
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
        LambdaQueryWrapper<BlogAboutMe> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(BlogAboutMe::getContent, BlogAboutMe::getEditorType);
        wrapper.last(" limit 1");
        BlogAboutMe blogAboutMe = blogAboutMeMapper.selectOne(wrapper);
        if (blogAboutMe == null) {
            return new BlogAboutMeVO();
        }

        return BlogAboutMeConverter.INSTANCE.convert(blogAboutMe);
    }

    /**
     * 编辑
     *
     * @return 关于 id
     */
    @Override
    public Long edit(BlogAboutMeEditDTO editDTO) {
        BlogAboutMe blogAboutMe = BlogAboutMeConverter.INSTANCE.convert(editDTO);
        if (blogAboutMe.getId() == null) {
            // 新增
            blogAboutMeMapper.insert(blogAboutMe);
        } else {
            // 修改
            blogAboutMeMapper.updateById(blogAboutMe);
        }
        return blogAboutMe.getId();
    }


}
