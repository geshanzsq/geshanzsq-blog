package com.geshanzsq.client.blog.config.service;

import com.geshanzsq.client.blog.config.vo.BlogThirdLoginConfigListVO;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import com.geshanzsq.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
public interface BlogThirdLoginConfigService extends BaseService<BlogThirdLoginConfig> {

    /**
     * 配置列表
     */
    List<BlogThirdLoginConfigListVO> listConfig();

    /**
     * 获取配置
     *
     * @param source 来源
     */
    BlogThirdLoginConfig getBySource(String source);
}
