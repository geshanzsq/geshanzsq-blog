package com.geshanzsq.admin.blog.comment.service.impl;

import com.geshanzsq.admin.blog.comment.dto.BlogCommentPageDTO;
import com.geshanzsq.admin.blog.comment.mapper.BlogCommentMapper;
import com.geshanzsq.admin.blog.comment.mapstruct.BlogCommentConverter;
import com.geshanzsq.admin.blog.comment.service.BlogCommentService;
import com.geshanzsq.admin.blog.comment.vo.BlogCommentVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.client.common.blog.util.MarkdownUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.system.user.service.SysUserService;
import com.geshanzsq.system.user.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/26
 */
@Service
public class BlogCommentServiceImpl extends BaseServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogCommentVO> pageList(BlogCommentPageDTO pageDTO) {
        pageDTO.setOrderColumn("gmtCreate");
        PageVO<BlogCommentVO> pageVO = BlogCommentConverter.INSTANCE.convert(blogCommentMapper.selectPage(pageDTO));
        if (CollectionUtils.isEmpty(pageVO.getList())) {
            return pageVO;
        }
        // 评论人用户 id
        Set<Long> userIds = pageVO.getList().stream().map(BlogCommentVO::getCreateUserId).collect(Collectors.toSet());
        // 获取评论人信息
        List<SysUserVO> userList = sysUserService.getUsernameAndNickNameByUserIds(new ArrayList<>(userIds));
        pageVO.getList().forEach(comment -> {
            SysUserVO user = userList.stream().filter(u -> comment.getCreateUserId().equals(u.getId())).findFirst().orElse(null);
            if (user != null) {
                comment.setUsername(user.getUsername());
                comment.setNickName(user.getNickName());
            }
        });
        return pageVO;
    }

    /**
     * 获取评论详情
     */
    @Override
    public BlogCommentVO getCommentById(Long id) {
        BlogComment blogComment = blogCommentMapper.selectById(id);
        if (blogComment == null) {
            return null;
        }
        BlogCommentVO blogCommentVO = BlogCommentConverter.INSTANCE.convert(blogComment);
        // 获取评论人信息
        List<SysUserVO> userList = sysUserService.getUsernameAndNickNameByUserIds(Arrays.asList(blogCommentVO.getCreateUserId()));
        if (!CollectionUtils.isEmpty(userList) && userList.size() > 0) {
            SysUserVO user = userList.get(0);
            blogCommentVO.setUsername(user.getUsername());
            blogCommentVO.setNickName(user.getNickName());
        }
        // 渲染 Markdown 内容
        blogCommentVO.setCommentContent(MarkdownUtils.markdownToHtml(blogCommentVO.getCommentContent()));
        return blogCommentVO;
    }
}
