package com.geshanzsq.system.dictionary.mapstruct;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.system.dictionary.dto.SysDictionaryDataAddDTO;
import com.geshanzsq.system.dictionary.dto.SysDictionaryDataUpdateDTO;
import com.geshanzsq.system.dictionary.po.SysDictionaryData;
import com.geshanzsq.system.dictionary.vo.SysDictionaryDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典数据对象转换
 *
 * @author geshanzsq
 * @date 2022/6/27
 */
@Mapper
public interface SysDictionaryDataConverter {

    SysDictionaryDataConverter INSTANCE = Mappers.getMapper(SysDictionaryDataConverter.class);

    SysDictionaryDataVO convert(SysDictionaryData sysDictionaryData);

    PageVO<SysDictionaryDataVO> convert(PageVO<SysDictionaryData> pageVo);

    SysDictionaryData convert(SysDictionaryDataAddDTO addDTO);

    SysDictionaryData convert(SysDictionaryDataUpdateDTO updateDTO);

}
