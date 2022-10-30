package com.geshanzsq.admin.blog.comment.mapper;

import com.geshanzsq.admin.blog.comment.dto.BlogCommentPageDTO;
import com.geshanzsq.client.common.blog.po.comment.BlogComment;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
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

    /**
     * 分页列表
     */
    default PageVO<BlogComment> selectPage(BlogCommentPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<BlogComment>()
                .eqIf(BlogComment::getStatus, pageDTO.getStatus())
                .orderByDesc(BlogComment::getGmtCreate)
        );
    }
}
