package com.geshanzsq.system.param.service;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;
import com.geshanzsq.system.param.dto.SysParamPageDTO;
import com.geshanzsq.admin.system.common.po.param.SysParam;
import com.geshanzsq.system.param.vo.SysParamVO;

/**
 * 参数配置
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public interface SysParamService extends BaseService<SysParam> {

    /**
     * 分页列表
     */
    PageVO<SysParamVO> pageList(SysParamPageDTO pageDTO);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
