package com.geshanzsq.admin.blog.config.service.impl;

import com.geshanzsq.admin.blog.config.mapper.BlogThirdLoginConfigMapper;
import com.geshanzsq.admin.blog.config.service.BlogThirdLoginConfigService;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 第三方登录配置
 *
 * @author geshanzsq
 * @date 2022/10/27
 */
@Service
public class BlogThirdLoginConfigServiceImpl extends BaseServiceImpl<BlogThirdLoginConfigMapper, BlogThirdLoginConfig> implements BlogThirdLoginConfigService {

    @Autowired
    private BlogThirdLoginConfigMapper blogThirdLoginConfigMapper;

}
