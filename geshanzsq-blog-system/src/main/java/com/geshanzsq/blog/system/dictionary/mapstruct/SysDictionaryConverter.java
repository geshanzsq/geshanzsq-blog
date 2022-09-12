package com.geshanzsq.blog.system.dictionary.mapstruct;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.system.dictionary.dto.SysDictionaryAddDTO;
import com.geshanzsq.blog.system.dictionary.dto.SysDictionaryUpdateDTO;
import com.geshanzsq.blog.system.dictionary.po.SysDictionary;
import com.geshanzsq.blog.system.dictionary.vo.SysDictionaryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典对象转换
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
@Mapper
public interface SysDictionaryConverter {

    SysDictionaryConverter INSTANCE = Mappers.getMapper(SysDictionaryConverter.class);


    SysDictionaryVO convert(SysDictionary sysDictionary);

    List<SysDictionaryVO> convertList(List<SysDictionary> list);

    PageVO<SysDictionaryVO> convert(PageVO<SysDictionary> pageVo);

    SysDictionary convert(SysDictionaryAddDTO addDTO);

    SysDictionary convert(SysDictionaryUpdateDTO updateDTO);

}
