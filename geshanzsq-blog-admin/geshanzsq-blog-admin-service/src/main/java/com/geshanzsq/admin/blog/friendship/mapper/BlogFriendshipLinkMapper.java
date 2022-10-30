package com.geshanzsq.admin.blog.friendship.mapper;

import com.geshanzsq.admin.blog.friendship.dto.BlogFriendshipLinkPageDTO;
import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
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
     * 分页列表
     */
    default PageVO<BlogFriendshipLink> selectPage(BlogFriendshipLinkPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<BlogFriendshipLink>()
                .likeIf(BlogFriendshipLink::getLinkName, pageDTO.getLinkName())
                .eqIf(BlogFriendshipLink::getStatus, pageDTO.getStatus())
        );
    }

    /**
     * 获取最大排序
     */
    Integer selectMaxSort();
}
