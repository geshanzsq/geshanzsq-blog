package com.geshanzsq.system.param.mapstruct;

import com.geshanzsq.admin.system.common.po.param.SysParam;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.system.param.dto.SysParamAddDTO;
import com.geshanzsq.system.param.dto.SysParamUpdateDTO;
import com.geshanzsq.system.param.vo.SysParamVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 参数配置对象转换
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Mapper
public interface SysParamConverter {

    SysParamConverter INSTANCE = Mappers.getMapper(SysParamConverter.class);

    PageVO<SysParamVO> convert(PageVO<SysParam> pageVO);

    SysParamVO convert(SysParam sysParam);

    SysParam convert(SysParamAddDTO addDTO);

    SysParam convert(SysParamUpdateDTO updateDTO);

}
