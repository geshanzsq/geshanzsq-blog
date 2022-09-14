package com.geshanzsq.system.param.service.impl;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.system.param.dto.SysParamPageDTO;
import com.geshanzsq.system.param.mapper.SysParamMapper;
import com.geshanzsq.system.param.mapstruct.SysParamConverter;
import com.geshanzsq.system.param.po.SysParam;
import com.geshanzsq.system.param.service.SysParamService;
import com.geshanzsq.system.param.vo.SysParamVO;
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
     * 分页列表
     */
    @Override
    public PageVO<SysParamVO> pageList(SysParamPageDTO pageDTO) {
        PageVO<SysParam> pageVO = sysParamMapper.selectPage(pageDTO);
        return SysParamConverter.INSTANCE.convert(pageVO);
    }

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return sysParamMapper.getMaxSort();
    }
}
