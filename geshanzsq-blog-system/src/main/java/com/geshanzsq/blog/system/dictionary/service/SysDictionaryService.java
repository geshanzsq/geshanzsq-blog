package com.geshanzsq.blog.system.dictionary.service;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import com.geshanzsq.blog.system.dictionary.dto.SysDictionaryPageDTO;
import com.geshanzsq.blog.system.dictionary.po.SysDictionary;
import com.geshanzsq.blog.system.dictionary.vo.DictionaryInfoVO;
import com.geshanzsq.blog.system.dictionary.vo.SysDictionaryVO;

import java.util.List;

/**
 * 字典
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
public interface SysDictionaryService extends BaseService<SysDictionary> {

    /**
     * 分页列表
     */
    PageVO<SysDictionaryVO> pageList(SysDictionaryPageDTO pageDTO);

    /**
     * 列表排序
     */
    List<SysDictionaryVO> listSort();

    /**
     * 删除
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();

    /**
     * 获取所有字典详细信息
     */
    List<DictionaryInfoVO> getAllDictionaryInfo();
}
