package com.geshanzsq.client.system.param.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.admin.system.common.po.param.SysParam;
import com.geshanzsq.client.system.param.mapper.SysParamMapper;
import com.geshanzsq.client.system.param.service.SysParamService;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 参数配置
 *
 * @author geshanzsq
 * @date 2022/9/28
 */
@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    @Autowired
    private SysParamMapper sysParamMapper;

    /**
     * 获取参数值
     *
     * @param paramKey 参数键
     */
    @Override
    public String getParamValueByKey(String paramKey) {
        if (StrUtils.isBlank(paramKey)) {
            return paramKey;
        }
        LambdaQueryWrapper<SysParam> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysParam::getParamKey, paramKey);
        SysParam sysParam = sysParamMapper.selectOne(wrapper);
        return sysParam == null ? null : sysParam.getParamValue();
    }
}
