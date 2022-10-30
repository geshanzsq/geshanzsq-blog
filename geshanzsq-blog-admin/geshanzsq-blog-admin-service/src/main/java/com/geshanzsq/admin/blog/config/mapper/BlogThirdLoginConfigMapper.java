package com.geshanzsq.admin.blog.config.mapper;

import com.geshanzsq.admin.blog.config.dto.BlogThirdLoginConfigPageDTO;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Mapper
public interface BlogThirdLoginConfigMapper extends BaseMapperPlus<BlogThirdLoginConfig> {

    /**
     * 分页列表
     */
    default PageVO<BlogThirdLoginConfig> selectPage(BlogThirdLoginConfigPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<BlogThirdLoginConfig>()
                .likeIf(BlogThirdLoginConfig::getThirdName, pageDTO.getThirdName())
                .likeIf(BlogThirdLoginConfig::getSource, pageDTO.getSource())
                .eqIf(BlogThirdLoginConfig::getHasOpenLogin, pageDTO.getHasOpenLogin())
        );
    }
}
