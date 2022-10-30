package com.geshanzsq.admin.blog.friendship.service.impl;


import com.geshanzsq.admin.blog.friendship.dto.BlogFriendshipLinkPageDTO;
import com.geshanzsq.admin.blog.friendship.mapper.BlogFriendshipLinkMapper;
import com.geshanzsq.admin.blog.friendship.mapstruct.BlogFriendshipLinkConverter;
import com.geshanzsq.admin.blog.friendship.service.BlogFriendshipLinkService;
import com.geshanzsq.admin.blog.friendship.vo.BlogFriendshipLinkVO;
import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Service
public class BlogFriendshipLinkServiceImpl extends BaseServiceImpl<BlogFriendshipLinkMapper, BlogFriendshipLink> implements BlogFriendshipLinkService {

    @Autowired
    private BlogFriendshipLinkMapper blogFriendshipLinkMapper;

    /**
     * 分页列表
     */
    @Override
    public PageVO<BlogFriendshipLinkVO> pageList(BlogFriendshipLinkPageDTO pageDTO) {
        PageVO<BlogFriendshipLink> pageVO = blogFriendshipLinkMapper.selectPage(pageDTO);
        return BlogFriendshipLinkConverter.INSTANCE.convert(pageVO);
    }

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return blogFriendshipLinkMapper.selectMaxSort();
    }
}
