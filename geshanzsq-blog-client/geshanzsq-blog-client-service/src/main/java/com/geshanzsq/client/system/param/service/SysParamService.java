package com.geshanzsq.client.system.param.service;

import com.geshanzsq.admin.system.common.po.param.SysParam;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 参数配置
 *
 * @author geshanzsq
 * @date 2022/9/28
 */
public interface SysParamService extends BaseService<SysParam> {

    /**
     * 获取参数值
     *
     * @param paramKey 参数键
     */
    String getParamValueByKey(String paramKey);

}
