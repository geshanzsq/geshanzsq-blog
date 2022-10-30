package com.geshanzsq.admin.blog.config.service;

import com.geshanzsq.admin.blog.config.dto.BlogThirdLoginConfigPageDTO;
import com.geshanzsq.admin.blog.config.vo.BlogThirdLoginConfigVO;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
public interface BlogThirdLoginConfigService extends BaseService<BlogThirdLoginConfig> {

    /**
     * 分页列表
     */
    PageVO<BlogThirdLoginConfigVO> pageList(BlogThirdLoginConfigPageDTO pageDTO);
}
