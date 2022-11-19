package com.geshanzsq.admin.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.admin.blog.article.dto.BlogArticlePageDTO;
import com.geshanzsq.admin.blog.article.dto.BlogArticleSaveDTO;
import com.geshanzsq.admin.blog.article.dto.BlogArticleUpdateDTO;
import com.geshanzsq.admin.blog.article.mapper.BlogArticleMapper;
import com.geshanzsq.admin.blog.article.mapstruct.BlogArticleConverter;
import com.geshanzsq.admin.blog.article.service.BlogArticleContentService;
import com.geshanzsq.admin.blog.article.service.BlogArticleService;
import com.geshanzsq.admin.blog.article.service.BlogArticleTagService;
import com.geshanzsq.admin.blog.article.vo.BlogArticleAuthorVO;
import com.geshanzsq.admin.blog.article.vo.BlogArticleSaveVO;
import com.geshanzsq.admin.blog.article.vo.BlogArticleVO;
import com.geshanzsq.admin.blog.recommend.service.BlogArticleRecommendService;
import com.geshanzsq.admin.blog.tag.service.BlogTagService;
import com.geshanzsq.admin.blog.tag.vo.BlogTagVO;
import com.geshanzsq.admin.system.common.po.user.SysUser;
import com.geshanzsq.client.common.blog.po.article.BlogArticle;
import com.geshanzsq.client.common.blog.po.article.BlogArticleContent;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.framework.mybatis.page.util.PageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.admin.system.user.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章
 *
 * @author geshanzsq
 * @date 2022/8/18
 */
@Service
public class BlogArticleServiceImpl extends BaseServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private BlogArticleContentService blogArticleContentService;
    @Autowired
    private BlogArticleTagService blogArticleTagService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogArticleRecommendService blogArticleRecommendService;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogArticleVO> pageList(BlogArticlePageDTO pageDTO) {
        PageUtils.startPage(pageDTO);
        PageVO<BlogArticleVO> pageVO = PageUtils.getPage(blogArticleMapper.selectPage(pageDTO));
        List<BlogArticleVO> list = pageVO.getList();
        if (CollectionUtils.isEmpty(list)) {
            return pageVO;
        }

        List<Long> idList = new ArrayList<>();
        List<Long> authorIdList = new ArrayList<>();
        list.forEach(article -> {
            idList.add(article.getId());
            authorIdList.add(article.getCreateUserId());
        });

        // 获取文章对应的标签
        List<BlogTagVO> tagList = blogTagService.getTagByArticleIds(idList);

        // 获取作者信息
        LambdaQueryWrapper<SysUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.in(SysUser::getId, authorIdList);
        userWrapper.select(SysUser::getId, SysUser::getNickName, SysUser::getUsername, SysUser::getAvatar);
        List<SysUser> authorList = sysUserMapper.selectList(userWrapper);

        list.forEach(article -> {
            // 标签
            List<BlogTagVO> articleTagList = tagList.stream()
                    .filter(t -> article.getId().equals(t.getArticleId()))
                    .collect(Collectors.toList());
            article.setTags(articleTagList);
            // 作者
            SysUser sysUser = authorList.stream().filter(author -> author.getId().equals(article.getCreateUserId()))
                    .findFirst().orElse(null);
            if (sysUser != null) {
                article.setAuthor(new BlogArticleAuthorVO(sysUser.getNickName(), sysUser.getUsername(), sysUser.getAvatar()));
            }
        });

        return pageVO;
    }

    /**
     * 获取文章信息
     */
    @Override
    public BlogArticleVO getArticleById(Long id) {
        // 1 获取文章基本信息
        BlogArticle blogArticle = blogArticleMapper.selectById(id);
        if (blogArticle == null) {
            throw new ParamException(MessageUtils.message("blog.article.not.exist"));
        }
        BlogArticleVO blogArticleVO = BlogArticleConverter.INSTANCE.convert(blogArticle);

        // 2 获取文章内容
        BlogArticleContent blogArticleContent = blogArticleContentService.getById(id);
        if (blogArticleContent != null) {
            blogArticleVO.setArticleContent(blogArticleContent.getArticleContent());
        }

        // 3 获取文章对应的标签
        List<BlogTagVO> tagList = blogTagService.getTagByArticleIds(Arrays.asList(id));
        blogArticleVO.setTags(tagList);

        // 4 获取推荐等级
        List<Integer> recommendLevels = blogArticleRecommendService.getRecommendLevel(id);
        blogArticleVO.setRecommendLevels(recommendLevels);
        return blogArticleVO;
    }

    /**
     * 保存文章
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogArticleSaveVO save(BlogArticleSaveDTO saveDTO) {
        // 1 保存文章信息
        BlogArticle blogArticle = BlogArticleConverter.INSTANCE.convert(saveDTO);
        Date gmtPublish = new Date();
        blogArticle.setGmtPublish(gmtPublish);
        blogArticle.setGmtLastPublish(gmtPublish);
        blogArticleMapper.insert(blogArticle);

        // 2 保存文章内容
        blogArticleContentService.save(blogArticle.getId(), saveDTO.getArticleContent());

        // 3 保存文章对应标签
        blogArticleTagService.save(blogArticle.getId(), saveDTO.getTagIds());

        // 4 保存推荐等级
        blogArticleRecommendService.save(blogArticle.getId(), saveDTO.getRecommendLevels());
        BlogArticleSaveVO blogArticleSaveVO = new BlogArticleSaveVO(blogArticle.getId());
        return blogArticleSaveVO;
    }

    /**
     * 修改文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BlogArticleUpdateDTO updateDTO) {
        Long articleId = updateDTO.getId();
        BlogArticle oldBlogArticle = blogArticleMapper.selectById(articleId);
        if (oldBlogArticle == null) {
            throw new ParamException(MessageUtils.message("blog.article.not.exist"));
        }

        // 1 更新文章信息
        BlogArticle blogArticle = BlogArticleConverter.INSTANCE.convert(updateDTO);
        blogArticle.setGmtLastPublish(new Date());
        blogArticleMapper.updateById(blogArticle);

        // 2 更新文章内容
        blogArticleContentService.update(articleId, updateDTO.getArticleContent());

        // 3 更新文章对应标签
        blogArticleTagService.update(articleId, updateDTO.getTagIds());

        // 4 更新推荐等级
        blogArticleRecommendService.update(articleId, updateDTO.getRecommendLevels());

    }

    /**
     * 删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        List<Long> articleIds = Arrays.asList(ids);
        // 删除文章
        blogArticleMapper.deleteBatchIds(articleIds);

        // 删除文章推荐
        blogArticleRecommendService.removeByArticleIds(articleIds);
    }

}
