package com.geshanzsq.blog.system.dictionary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.blog.common.core.enums.CommonStatus;
import com.geshanzsq.blog.common.core.exception.ParamException;
import com.geshanzsq.blog.common.core.util.message.MessageUtils;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.blog.system.dictionary.dto.SysDictionaryPageDTO;
import com.geshanzsq.blog.system.dictionary.mapper.SysDictionaryDataMapper;
import com.geshanzsq.blog.system.dictionary.mapper.SysDictionaryMapper;
import com.geshanzsq.blog.system.dictionary.mapstruct.SysDictionaryConverter;
import com.geshanzsq.blog.system.dictionary.po.SysDictionary;
import com.geshanzsq.blog.system.dictionary.po.SysDictionaryData;
import com.geshanzsq.blog.system.dictionary.service.SysDictionaryService;
import com.geshanzsq.blog.system.dictionary.vo.DictionaryInfoVO;
import com.geshanzsq.blog.system.dictionary.vo.SysDictionaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 字典
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
@Service
public class SysDictionaryServiceImpl extends BaseServiceImpl<SysDictionaryMapper, SysDictionary> implements SysDictionaryService {

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;
    @Autowired
    private SysDictionaryDataMapper sysDictionaryDataMapper;

    /**
     * 分页列表
     */
    @Override
    public PageVO<SysDictionaryVO> pageList(SysDictionaryPageDTO pageDTO) {
        PageVO<SysDictionary> page = sysDictionaryMapper.selectPage(pageDTO);
        return SysDictionaryConverter.INSTANCE.convert(page);
    }

    /**
     * 列表排序
     */
    @Override
    public List<SysDictionaryVO> listSort() {
        LambdaQueryWrapper<SysDictionary> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SysDictionary::getSort, SysDictionary::getId);
        return SysDictionaryConverter.INSTANCE.convertList(sysDictionaryMapper.selectList(wrapper));
    }

    /**
     * 删除
     */
    @Override
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        // 判断字典是否有字典数据，如果有，则不能删除
        LambdaQueryWrapper<SysDictionaryData> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysDictionaryData::getDictionaryId, ids);
        if (sysDictionaryDataMapper.selectCount(wrapper) > 0) {
            throw new ParamException(MessageUtils.message("dictionary.exist.data"));
        }

        // 删除数据
        sysDictionaryMapper.deleteBatchIds(Arrays.asList(ids));
    }


    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return sysDictionaryMapper.getMaxSort();
    }

    /**
     * 获取所有字典详细信息
     */
    @Override
    public List<DictionaryInfoVO> getAllDictionaryInfo() {
        return sysDictionaryMapper.getAllDictionaryInfo(CommonStatus.NORMAL.code);
    }
}
