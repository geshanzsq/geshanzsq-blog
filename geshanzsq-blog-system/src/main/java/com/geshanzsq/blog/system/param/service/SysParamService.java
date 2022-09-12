package com.geshanzsq.blog.system.param.service;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import com.geshanzsq.blog.system.param.dto.SysParamPageDTO;
import com.geshanzsq.blog.system.param.po.SysParam;
import com.geshanzsq.blog.system.param.vo.SysParamVO;

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
