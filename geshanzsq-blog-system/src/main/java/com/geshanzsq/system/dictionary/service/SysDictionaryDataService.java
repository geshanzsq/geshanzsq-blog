package com.geshanzsq.system.dictionary.service;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.BaseService;
import com.geshanzsq.system.dictionary.dto.SysDictionaryDataPageDTO;
import com.geshanzsq.system.dictionary.po.SysDictionaryData;
import com.geshanzsq.system.dictionary.vo.SysDictionaryDataVO;

/**
 * 字典数据
 *
 * @author geshanzsq
 * @date 2022/6/27
 */
public interface SysDictionaryDataService extends BaseService<SysDictionaryData> {

    /**
     * 分页列表
     */
    PageVO<SysDictionaryDataVO> pageList(SysDictionaryDataPageDTO pageDTO);

    /**
     * 获取最大排序
     * @param dictionaryId 字典 id
     */
    Integer getMaxSortByDictionaryId(Long dictionaryId);

}
