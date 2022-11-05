package com.geshanzsq.system.param.service;

import com.geshanzsq.admin.system.common.po.param.SysParam;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 参数配置
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public interface SysParamService extends BaseService<SysParam> {

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
