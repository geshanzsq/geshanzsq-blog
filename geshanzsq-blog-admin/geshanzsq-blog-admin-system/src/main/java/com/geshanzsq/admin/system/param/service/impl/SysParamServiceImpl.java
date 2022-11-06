package com.geshanzsq.admin.system.param.service.impl;

import com.geshanzsq.admin.system.common.po.param.SysParam;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.admin.system.param.mapper.SysParamMapper;
import com.geshanzsq.admin.system.param.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 参数配置
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    @Autowired
    private SysParamMapper sysParamMapper;

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return sysParamMapper.selectMaxSort();
    }
}
