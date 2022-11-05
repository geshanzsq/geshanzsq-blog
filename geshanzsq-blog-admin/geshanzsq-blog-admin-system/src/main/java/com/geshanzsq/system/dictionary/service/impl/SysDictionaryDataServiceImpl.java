package com.geshanzsq.system.dictionary.service.impl;

import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.system.dictionary.mapper.SysDictionaryDataMapper;
import com.geshanzsq.system.dictionary.po.SysDictionaryData;
import com.geshanzsq.system.dictionary.service.SysDictionaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典数据
 *
 * @author geshanzsq
 * @date 2022/6/27
 */
@Service
public class SysDictionaryDataServiceImpl extends BaseServiceImpl<SysDictionaryDataMapper, SysDictionaryData> implements SysDictionaryDataService {

    @Autowired
    private SysDictionaryDataMapper sysDictionaryDataMapper;

    /**
     * 获取最大排序
     * @param dictionaryId 字典 id
     */
    @Override
    public Integer getMaxSortByDictionaryId(Long dictionaryId) {
        return sysDictionaryDataMapper.selectMaxSortByDictionaryId(dictionaryId);
    }
}
