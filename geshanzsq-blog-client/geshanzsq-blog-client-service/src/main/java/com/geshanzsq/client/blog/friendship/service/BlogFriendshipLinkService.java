package com.geshanzsq.client.blog.friendship.service;

import com.geshanzsq.client.blog.friendship.vo.BlogFriendshipLinkListVO;
import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
public interface BlogFriendshipLinkService extends BaseService<BlogFriendshipLink> {

    /**
     * 列表
     */
    List<BlogFriendshipLinkListVO> listLink();
}
