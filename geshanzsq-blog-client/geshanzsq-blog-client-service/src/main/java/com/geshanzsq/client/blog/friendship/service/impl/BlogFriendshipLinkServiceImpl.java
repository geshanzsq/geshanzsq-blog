package com.geshanzsq.client.blog.friendship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.friendship.mapper.BlogFriendshipLinkMapper;
import com.geshanzsq.client.blog.friendship.mapstruct.BlogFriendshipLinkConverter;
import com.geshanzsq.client.blog.friendship.service.BlogFriendshipLinkService;
import com.geshanzsq.client.blog.friendship.vo.BlogFriendshipLinkListVO;
import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import com.geshanzsq.common.core.enums.CommonStatus;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 列表
     */
    @Override
    public List<BlogFriendshipLinkListVO> listLink() {
        LambdaQueryWrapper<BlogFriendshipLink> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogFriendshipLink::getStatus, CommonStatus.NORMAL.code);
        wrapper.select(BlogFriendshipLink::getLinkName, BlogFriendshipLink::getLinkUrl);
        wrapper.orderByAsc(BlogFriendshipLink::getSort);
        List<BlogFriendshipLink> list = blogFriendshipLinkMapper.selectList(wrapper);
        return BlogFriendshipLinkConverter.INSTANCE.convert(list);
    }
}
