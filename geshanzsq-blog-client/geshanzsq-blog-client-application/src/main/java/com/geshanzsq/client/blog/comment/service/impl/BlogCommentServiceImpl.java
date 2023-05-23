package com.geshanzsq.client.blog.comment.service.impl;

import com.geshanzsq.client.blog.article.service.BlogArticleService;
import com.geshanzsq.client.blog.comment.dto.BlogCommentPageDTO;
import com.geshanzsq.client.blog.comment.dto.BlogCommentSaveDTO;
import com.geshanzsq.client.blog.comment.mapper.BlogCommentMapper;
import com.geshanzsq.client.blog.comment.mapstruct.BlogCommentConverter;
import com.geshanzsq.client.blog.comment.service.BlogCommentService;
import com.geshanzsq.client.blog.comment.vo.BlogArticleCommentListVO;
import com.geshanzsq.client.blog.comment.vo.BlogCommentPageVO;
import com.geshanzsq.client.common.blog.enums.comment.BlogCommentStatus;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.client.common.blog.util.MarkdownUtils;
import com.geshanzsq.common.core.enums.YesNo;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
@Service
public class BlogCommentServiceImpl extends BaseServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogCommentPageVO> pageList(BlogCommentPageDTO pageDTO) {
        pageDTO.setOrderColumn("gmtCreate");
        pageDTO.setOrderType("desc");
        pageDTO.setCreateUserId(SecurityUtils.getUserId());
        PageVO<BlogCommentPageVO> pageVO = BlogCommentConverter.INSTANCE.convert(blogCommentMapper.selectPage(pageDTO));
        pageVO.getList().forEach(comment -> {
            // 渲染 Markdown 内容
            comment.setCommentContent(MarkdownUtils.markdownToHtml(comment.getCommentContent()));
        });
        return pageVO;
    }

    /**
     * 新增
     */
    @Override
    public void add(BlogCommentSaveDTO saveDTO) {
        // 1、校验文章是否存在并获取创建人用户 id，如果为 0，为留言板
        Long createUserId = null;
        if (saveDTO.getArticleId() > 0) {
            createUserId = blogArticleService.getCreateUserId(saveDTO.getArticleId());
        }

        // 2、新增评论
        Long userId = SecurityUtils.getUserId();
        BlogComment blogComment = BlogCommentConverter.INSTANCE.convert(saveDTO);
        blogComment.setCreateUserId(userId);
        // 如果为作者，评论不用审核
        Integer status = userId.equals(createUserId) ? BlogCommentStatus.PASS.code : BlogCommentStatus.AUDIT.code;
        blogComment.setStatus(status);
        blogComment.setHasSticky(YesNo.NO.code);
        blogCommentMapper.insert(blogComment);
    }

    /**
     * 获取文章评论
     */
    @Override
    public List<BlogArticleCommentListVO> listByArticleId(Long articleId) {
        if (articleId == null) {
            return new ArrayList<>();
        }
        // 1、校验文章是否存在并获取作者
        Long createUserId = null;
        // 如果为文章，获取作者，否则为留言板
        if (articleId > 0) {
            createUserId = blogArticleService.getCreateUserId(articleId);
        }

        // 2、获取评论数据
        List<BlogArticleCommentListVO> list = blogCommentMapper.selectListByArticleId(articleId, BlogCommentStatus.PASS.code);
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }

        // 3、是否为作者
        if (createUserId != null) {
            for (BlogArticleCommentListVO comment : list) {
                comment.setHasAuthor(createUserId.equals(comment.getCreateUserId()));
            }
        }

        // 4、渲染 Markdown 内容
        list.forEach(comment -> {
            comment.setCommentContent(MarkdownUtils.markdownToHtml(comment.getCommentContent()));
        });

        // 5、构造树形结构
        return buildTree(list);
    }

    private List<BlogArticleCommentListVO> buildTree(List<BlogArticleCommentListVO> list) {
        List<BlogArticleCommentListVO> resultList = buildCommentTree(list);
        return resultList;
    }

    private List<BlogArticleCommentListVO> buildCommentTree(List<BlogArticleCommentListVO> list) {
        List<BlogArticleCommentListVO> resultList = new ArrayList<>();
        for (Iterator<BlogArticleCommentListVO> iterator = list.iterator(); iterator.hasNext();) {
            BlogArticleCommentListVO comment = iterator.next();
            if (comment.getParentId() == 0) {
                recursionFn(list,comment);
                resultList.add(comment);
            }
        }
        if (resultList.isEmpty()) {
            resultList = list;
        }
        return resultList;
    }

    /**
     * 递归获取
     */
    private void recursionFn(List<BlogArticleCommentListVO> list,BlogArticleCommentListVO t) {
        List<BlogArticleCommentListVO> childrenList = getChildrenList(list, t);
        t.setChildren(childrenList);
        for (BlogArticleCommentListVO children : childrenList) {
            List<BlogArticleCommentListVO> tChildren = getChildrenList(list, children);
            if (tChildren.size() > 0) {
                recursionFn(list,children);
            }
        }
    }

    /**
     * 获取子节点
     */
    private List<BlogArticleCommentListVO> getChildrenList(List<BlogArticleCommentListVO> list,BlogArticleCommentListVO t) {
        List<BlogArticleCommentListVO> childrenList = new ArrayList<>();
        for (BlogArticleCommentListVO children : list) {
            if (t.getId().equals(children.getParentId())) {
                childrenList.add(children);
            }
        }
        return childrenList;
    }


}
