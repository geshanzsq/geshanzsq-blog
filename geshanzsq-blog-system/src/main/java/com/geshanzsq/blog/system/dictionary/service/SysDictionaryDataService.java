package com.geshanzsq.blog.system.dictionary.service;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import com.geshanzsq.blog.system.dictionary.dto.SysDictionaryDataPageDTO;
import com.geshanzsq.blog.system.dictionary.po.SysDictionaryData;
import com.geshanzsq.blog.system.dictionary.vo.SysDictionaryDataVO;

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
