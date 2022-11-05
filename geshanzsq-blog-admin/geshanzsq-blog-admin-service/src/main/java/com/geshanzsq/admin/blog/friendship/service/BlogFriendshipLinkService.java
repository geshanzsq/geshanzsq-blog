package com.geshanzsq.admin.blog.friendship.service;

import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
public interface BlogFriendshipLinkService extends BaseService<BlogFriendshipLink> {

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
