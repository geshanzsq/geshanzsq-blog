package com.geshanzsq.admin.blog.comment.mapper;

import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论
 *
 * @author geshanzsq
 * @date 2022/10/26
 */
@Mapper
public interface BlogCommentMapper extends BaseMapperPlus<BlogComment> {

}
