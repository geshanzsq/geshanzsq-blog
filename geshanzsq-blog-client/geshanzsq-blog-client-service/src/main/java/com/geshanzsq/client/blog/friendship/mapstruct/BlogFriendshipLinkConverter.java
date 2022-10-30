package com.geshanzsq.client.blog.friendship.mapstruct;

import com.geshanzsq.client.blog.friendship.vo.BlogFriendshipLinkListVO;
import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 友情链接对象转换
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Mapper
public interface BlogFriendshipLinkConverter {

    BlogFriendshipLinkConverter INSTANCE = Mappers.getMapper(BlogFriendshipLinkConverter.class);

    List<BlogFriendshipLinkListVO> convert(List<BlogFriendshipLink> list);

}
