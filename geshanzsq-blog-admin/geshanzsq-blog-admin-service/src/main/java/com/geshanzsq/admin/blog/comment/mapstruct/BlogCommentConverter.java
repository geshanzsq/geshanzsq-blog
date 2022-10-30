package com.geshanzsq.admin.blog.comment.mapstruct;

import com.geshanzsq.admin.blog.comment.dto.BlogCommentAuditDTO;
import com.geshanzsq.admin.blog.comment.vo.BlogCommentVO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 评论对象转换
 *
 * @author geshanzsq
 * @date 2022/10/26
 */
@Mapper
public interface BlogCommentConverter {

    BlogCommentConverter INSTANCE = Mappers.getMapper(BlogCommentConverter.class);

    PageVO<BlogCommentVO> convert(PageVO<BlogComment> pageVO);

    BlogCommentVO convert(BlogComment blogComment);

    BlogComment convert(BlogCommentAuditDTO auditDTO);

}
