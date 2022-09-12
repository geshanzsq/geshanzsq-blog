package com.geshanzsq.blog.system.param.service.impl;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.blog.system.param.dto.SysParamPageDTO;
import com.geshanzsq.blog.system.param.mapper.SysParamMapper;
import com.geshanzsq.blog.system.param.mapstruct.SysParamConverter;
import com.geshanzsq.blog.system.param.po.SysParam;
import com.geshanzsq.blog.system.param.service.SysParamService;
import com.geshanzsq.blog.system.param.vo.SysParamVO;
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
