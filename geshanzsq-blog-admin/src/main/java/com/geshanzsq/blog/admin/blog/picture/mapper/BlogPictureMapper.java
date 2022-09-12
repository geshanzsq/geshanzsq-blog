package com.geshanzsq.blog.admin.blog.picture.mapper;

import com.geshanzsq.blog.admin.blog.picture.dto.BlogPicturePageDTO;
import com.geshanzsq.blog.admin.blog.picture.po.BlogPicture;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片
 *
 * @author geshanzsq
 * @date 2022/8/24
 */
@Mapper
public interface BlogPictureMapper extends BaseMapperPlus<BlogPicture> {

    /**
     * 分页
     */
    default PageVO<BlogPicture> selectPage(BlogPicturePageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<BlogPicture>()
                .eqIf(BlogPicture::getCategoryId, pageDTO.getCategoryId())
                .likeIf(BlogPicture::getPictureOriginalName, pageDTO.getPictureOriginalName())
                .orderByDesc(BlogPicture::getGmtCreate)
        );
    }
}
