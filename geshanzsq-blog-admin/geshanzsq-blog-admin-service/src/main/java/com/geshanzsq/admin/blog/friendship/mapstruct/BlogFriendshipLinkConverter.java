package com.geshanzsq.admin.blog.friendship.mapstruct;

import com.geshanzsq.admin.blog.friendship.dto.BlogFriendshipLinkAddDTO;
import com.geshanzsq.admin.blog.friendship.dto.BlogFriendshipLinkUpdateDTO;
import com.geshanzsq.admin.blog.friendship.vo.BlogFriendshipLinkVO;
import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 友情链接对象转换
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Mapper
public interface BlogFriendshipLinkConverter {

    BlogFriendshipLinkConverter INSTANCE = Mappers.getMapper(BlogFriendshipLinkConverter.class);

    PageVO<BlogFriendshipLinkVO> convert(PageVO<BlogFriendshipLink> pageVO);

    BlogFriendshipLinkVO convert(BlogFriendshipLink blogFriendshipLink);

    BlogFriendshipLink convert(BlogFriendshipLinkAddDTO addDTO);

    BlogFriendshipLink convert(BlogFriendshipLinkUpdateDTO updateDTO);

}
