package com.geshanzsq.system.dictionary.mapper;

import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.system.dictionary.po.SysDictionary;
import com.geshanzsq.system.dictionary.vo.DictionaryInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
@Mapper
public interface SysDictionaryMapper extends BaseMapperPlus<SysDictionary> {

    /**
     * 获取最大排序
     */
    Integer selectMaxSort();

    /**
     * 获取所有字典详细信息
     * @param status 状态
     */
    List<DictionaryInfoVO> getAllDictionaryInfo(Integer status);

}
