package com.geshanzsq.client.blog.config.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.config.mapper.BlogThirdLoginConfigMapper;
import com.geshanzsq.client.blog.config.mapstruct.BlogThirdLoginConfigConverter;
import com.geshanzsq.client.blog.config.service.BlogThirdLoginConfigService;
import com.geshanzsq.client.blog.config.vo.BlogThirdLoginConfigListVO;
import com.geshanzsq.client.common.blog.po.config.BlogThirdLoginConfig;
import com.geshanzsq.common.core.enums.YesNo;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    /**
     * 配置列表
     */
    @Override
    public List<BlogThirdLoginConfigListVO> listConfig() {
        LambdaQueryWrapper<BlogThirdLoginConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogThirdLoginConfig::getHasOpenLogin, YesNo.YES.code);
        wrapper.select(BlogThirdLoginConfig::getId, BlogThirdLoginConfig::getThirdName, BlogThirdLoginConfig::getSource);
        List<BlogThirdLoginConfig> list = blogThirdLoginConfigMapper.selectList(wrapper);
        return BlogThirdLoginConfigConverter.INSTANCE.convert(list);
    }

    /**
     * 获取配置
     *
     * @param source 来源
     */
    @Override
    public BlogThirdLoginConfig getBySource(String source) {
        if (StrUtils.isBlank(source)) {
            return null;
        }
        LambdaQueryWrapper<BlogThirdLoginConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogThirdLoginConfig::getSource, source);
        List<BlogThirdLoginConfig> list = blogThirdLoginConfigMapper.selectList(wrapper);
        return !CollectionUtils.isEmpty(list) && list.size() > 0 ? list.get(0) : null;
    }
}
