package com.geshanzsq.client.blog.comment.mapstruct;

import com.geshanzsq.client.blog.comment.dto.BlogCommentSaveDTO;
import com.geshanzsq.client.blog.comment.vo.BlogCommentPageVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 评论对象转换
 *
 * @author geshanzsq
 * @date 2022/10/22
 */
@Mapper
public interface BlogCommentConverter {

    BlogCommentConverter INSTANCE = Mappers.getMapper(BlogCommentConverter.class);

    BlogComment convert(BlogCommentSaveDTO saveDTO);

    PageVO<BlogCommentPageVO> convert(PageVO<BlogComment> pageVO);

}
