package com.geshanzsq.admin.blog.friendship.mapper;

import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Mapper
public interface BlogFriendshipLinkMapper extends BaseMapperPlus<BlogFriendshipLink> {

    /**
     * 获取最大排序
     */
    Integer selectMaxSort();
}
