package com.geshanzsq.client.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.geshanzsq.client.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.client.blog.article.dto.BlogArticleSearchPageDTO;
import com.geshanzsq.client.blog.article.mapper.BlogArticleContentMapper;
import com.geshanzsq.client.blog.article.mapper.BlogArticleMapper;
import com.geshanzsq.client.blog.article.mapstruct.BlogArticleConverter;
import com.geshanzsq.client.blog.article.service.BlogArticleService;
import com.geshanzsq.client.blog.article.vo.*;
import com.geshanzsq.client.blog.category.service.BlogCategoryService;
import com.geshanzsq.client.blog.collect.service.BlogArticleCollectService;
import com.geshanzsq.client.blog.collect.vo.BlogArticleCollectCountVO;
import com.geshanzsq.client.blog.footprint.service.BlogArticleFootprintService;
import com.geshanzsq.client.blog.like.service.BlogArticleLikeService;
import com.geshanzsq.client.blog.like.vo.BlogArticleLikeCountVO;
import com.geshanzsq.client.blog.tag.service.BlogTagService;
import com.geshanzsq.client.common.blog.enums.article.BlogArticleStatus;
import com.geshanzsq.client.common.blog.enums.article.EditorType;
import com.geshanzsq.client.common.blog.po.article.BlogArticle;
import com.geshanzsq.client.common.blog.po.article.BlogArticleContent;
import com.geshanzsq.client.common.blog.po.category.BlogCategory;
import com.geshanzsq.client.common.blog.util.MarkdownUtils;
import com.geshanzsq.client.system.param.constant.SysParamConstant;
import com.geshanzsq.client.system.param.service.SysParamService;
import com.geshanzsq.client.system.user.service.SysUserService;
import com.geshanzsq.common.core.enums.OpenStatus;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.common.framework.manager.AsyncManager;
import com.geshanzsq.common.framework.mybatis.page.util.PageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * ??????
 *
 * @author geshanzsq
 * @date 2022/9/16
 */
@Service
public class BlogArticleServiceImpl extends BaseServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;
    @Autowired
    private BlogArticleContentMapper blogArticleContentMapper;

    @Autowired
    private BlogArticleLikeService blogArticleLikeService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private BlogArticleCollectService blogArticleCollectService;
    @Autowired
    private BlogArticleFootprintService blogArticleFootprintService;

    /**
     * ????????????
     */
    @Override
    public PageVO<BlogArticlePageVO> pageList(BlogArticlePageDTO pageDTO) {
        pageDTO.setStatus(BlogArticleStatus.PUBLISH.code);
        PageUtils.startPage(pageDTO);
        List<BlogArticlePageVO> list = blogArticleMapper.selectPage(pageDTO);
        PageVO<BlogArticlePageVO> pageVO = PageUtils.getPage(list);
        // ?????????????????????
        setArticleLikeCount(pageVO.getList());
        return pageVO;
    }

    /**
     * ??????????????????
     */
    @Override
    public PageVO<BlogArticlePageVO> pageSearchList(BlogArticleSearchPageDTO pageDTO) {
        pageDTO.setStatus(BlogArticleStatus.PUBLISH.code);
        PageUtils.startPage(pageDTO);
        // ???????????????????????????
        if (StrUtils.isNotBlank(pageDTO.getArchiveGmPublishLine())) {
            String archiveGmPublishLine = pageDTO.getArchiveGmPublishLine();
            archiveGmPublishLine = archiveGmPublishLine.replace("???","-");
            archiveGmPublishLine = archiveGmPublishLine.replace("???","");
            pageDTO.setArchiveGmPublishLine(archiveGmPublishLine);
        }
        List<BlogArticlePageVO> list = blogArticleMapper.selectSearchPage(pageDTO);
        PageVO<BlogArticlePageVO> pageVO = PageUtils.getPage(list);
        // ?????????????????????
        setArticleLikeCount(pageVO.getList());
        // ?????????????????????
        blogCategoryService.updateClickCountByCategoryName(pageDTO.getCategoryName());
        // ?????????????????????
        blogTagService.updateClickCountByTagName(pageDTO.getTagName());
        return pageVO;
    }

    /**
     * ????????????
     * @param recommendLevel ????????????
     */
    @Override
    public List<BlogArticleRecommendVO> getRecommend(Integer recommendLevel) {
        List<BlogArticleRecommendVO> list = new ArrayList<>();
        if (recommendLevel == null) {
            return list;
        }
        String paramKey = null;
        if (recommendLevel.equals(1)) {
            paramKey = SysParamConstant.BLOG_ARTICLE_ONE_RECOMMEND;
        } else if (recommendLevel.equals(2)) {
            paramKey = SysParamConstant.BLOG_ARTICLE_SECOND_RECOMMEND;
        } else if (recommendLevel.equals(3)) {
            paramKey = SysParamConstant.BLOG_ARTICLE_THIRD_RECOMMEND;
        } else {
            // ?????????????????????
            return list;
        }
        // ??????????????????
        String paramValue = sysParamService.getParamValueByKey(paramKey);

        // ????????????????????????????????????
        if (StrUtils.isBlank(paramValue)) {
            return list;
        }

        int limitCount = Integer.parseInt(paramValue);
        list = blogArticleMapper.selectRecommend(recommendLevel, BlogArticleStatus.PUBLISH.code, limitCount);
        return list;
    }

    /**
     * ???????????????
     */
    @Override
    public List<BlogArticleRecommendVO> listClickRanking() {
        return blogArticleMapper.selectClickRanking(BlogArticleStatus.PUBLISH.code, 5);
    }

    /**
     * ?????????????????????
     */
    @Override
    public List<BlogArticleCategoryCountVO> getArticleCategoryCount() {
        return blogArticleMapper.selectArticleCategoryCount(BlogArticleStatus.PUBLISH.code);
    }

    /**
     * ?????????????????????
     */
    @Override
    public List<String> getArchiveGmtPublishLine() {
        return blogArticleMapper.selectArchiveGmtPublishLine(BlogArticleStatus.PUBLISH.code);
    }

    /**
     * ??????????????????
     */
    @Override
    public BlogArticleVO getArticleById(Long id) {
        // 1??????????????????????????????
        BlogArticle blogArticle = getPublishArticleById(id);
        BlogArticleVO blogArticleVO = BlogArticleConverter.INSTANCE.convert(blogArticle);

        // 2?????????????????????
        LambdaQueryWrapper<BlogCategory> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.eq(BlogCategory::getId, blogArticle.getCategoryId());
        categoryWrapper.select(BlogCategory::getCategoryName);
        BlogCategory blogCategory = blogCategoryService.getOne(categoryWrapper);
        if (blogCategory != null) {
            blogArticleVO.setCategoryName(blogCategory.getCategoryName());
        }

        // 3?????????????????????
        List<String> tagNames = blogTagService.getTagNameByArticleId(id);
        blogArticleVO.setTagNames(tagNames);

        // 4?????????????????????
        BlogArticleAuthorVO authorVO = sysUserService.getAuthorById(blogArticle.getCreateUserId());
        blogArticleVO.setAuthor(authorVO);

        // 5??????????????????????????????
        long viewCount = blogArticleVO.getViewCount() != null ? blogArticleVO.getViewCount() + 1 : 1L;
        blogArticleVO.setViewCount(viewCount);
        AsyncManager.me().execute(new TimerTask() {
            @Override
            public void run() {
                blogArticleMapper.updateArticleViewCount(id);
            }
        });

        // 6?????????????????????????????????????????????????????????????????????????????????
        Integer hasComment = blogArticle.getHasComment();
        if (OpenStatus.YES.code.equals(hasComment)) {
            String isOpen = sysParamService.getParamValueByKey(SysParamConstant.BLOG_ARTICLE_COMMENT_OPEN);
            hasComment = StrUtils.isNotBlank(isOpen) && Boolean.valueOf(isOpen) ? OpenStatus.YES.code : OpenStatus.NO.code;
            blogArticleVO.setHasComment(hasComment);
        }

        // 7????????????????????????
        List<BlogArticleLikeCountVO> likeCountList = blogArticleLikeService.getCountByArticleIds(Arrays.asList(id));
        Long likeCount = CollectionUtils.isEmpty(likeCountList) ? 0L : likeCountList.get(0).getCount();
        blogArticleVO.setLikeCount(likeCount);

        // 8????????????????????????
        List<BlogArticleCollectCountVO> collectCountList = blogArticleCollectService.getCountByArticleIds(Arrays.asList(id));
        Long collectCount = CollectionUtils.isEmpty(collectCountList) ? 0L : collectCountList.get(0).getCount();
        blogArticleVO.setCollectCount(collectCount);

        // 9???????????????????????????
        try {
            Long userId = SecurityUtils.getUserId();
            AsyncManager.me().execute(new TimerTask() {
                @Override
                public void run() {
                    blogArticleFootprintService.save(userId, id);
                }
            });
        } catch (Exception e) {}

        return blogArticleVO;
    }

    /**
     * ??????????????????
     */
    @Override
    public BlogArticleContentVO getContentById(Long id) {
        // 1??????????????????????????????
        BlogArticle blogArticle = getPublishArticleById(id, BlogArticle::getEditorType);

        // 2?????????????????????
        BlogArticleContent blogArticleContent = blogArticleContentMapper.selectById(id);
        BlogArticleContentVO blogArticleContentVO = new BlogArticleContentVO();
        if (blogArticleContent != null) {
            String articleContent = blogArticleContent.getArticleContent();
            // Markdown ??? Html
            if (EditorType.MARKDOWN.code.equals(blogArticle.getEditorType())) {
                articleContent = MarkdownUtils.markdownToHtml(articleContent);
            }
            blogArticleContentVO.setArticleContent(articleContent);
        }
        return blogArticleContentVO;
    }

    /**
     * ??????????????????
     */
    @Override
    public List<BlogArticleAboutVO> getAboutArticleById(Long id) {
        // ????????? 6 ?????????
        int limitCount = 6;
        String paramValue = sysParamService.getParamValueByKey(SysParamConstant.BLOG_ARTICLE_ABOUT);
        if (StrUtils.isNotBlank(paramValue)) {
            limitCount = Integer.parseInt(paramValue);
        }
        return blogArticleMapper.selectAboutArticleById(id, BlogArticleStatus.PUBLISH.code, limitCount);
    }

    /**
     * ????????????????????????
     *
     * @param id ?????? id
     */
    @Override
    public void checkArticleExist(Long id) {
        LambdaQueryWrapper<BlogArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogArticle::getId, id);
        wrapper.eq(BlogArticle::getStatus, BlogArticleStatus.PUBLISH.code);
        if (blogArticleMapper.selectCount(wrapper) == 0) {
            throw new ParamException(MessageUtils.message("blog.article.not.exist"));
        }
    }

    /**
     * ????????????????????? id
     *
     * @param id ?????? id
     * @return ??????????????? id
     */
    @Override
    public Long getCreateUserId(Long id) {
        LambdaQueryWrapper<BlogArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogArticle::getId, id);
        wrapper.eq(BlogArticle::getStatus, BlogArticleStatus.PUBLISH.code);
        wrapper.select(BlogArticle::getCreateUserId);
        BlogArticle article = blogArticleMapper.selectOne(wrapper);
        if (article == null) {
            throw new ParamException(MessageUtils.message("blog.article.not.exist"));
        }
        return article.getCreateUserId();
    }

    /**
     * ?????????????????????
     */
    private void setArticleLikeCount(List<BlogArticlePageVO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        // ???????????????
        List<Long> articleIds = list.stream().map(a -> a.getId()).collect(Collectors.toList());
        List<BlogArticleLikeCountVO> likeCountList = blogArticleLikeService.getCountByArticleIds(articleIds);
        list.forEach(article -> {
            BlogArticleLikeCountVO likeCountVO = likeCountList.stream().filter(l -> article.getId().equals(l.getArticleId())).findFirst().orElse(null);
            Long likeCount = likeCountVO != null ? likeCountVO.getCount() : 0L;
            article.setLikeCount(likeCount);
        });
    }

    /**
     * ????????????????????????
     *
     * @param id ?????? id
     * @param selectColumns ????????????
     */
    private BlogArticle getPublishArticleById(Long id, SFunction<BlogArticle, ?>... selectColumns) {
        LambdaQueryWrapper<BlogArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogArticle::getId, id);
        wrapper.eq(BlogArticle::getStatus, BlogArticleStatus.PUBLISH.code);
        wrapper.select(selectColumns);
        BlogArticle blogArticle = blogArticleMapper.selectOne(wrapper);
        if (blogArticle == null) {
            throw new ParamException(MessageUtils.message("blog.article.not.exist"));
        }
        return blogArticle;
    }
}
