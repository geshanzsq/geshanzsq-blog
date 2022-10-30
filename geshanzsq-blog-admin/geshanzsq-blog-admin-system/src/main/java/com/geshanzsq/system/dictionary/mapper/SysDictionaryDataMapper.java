package com.geshanzsq.system.dictionary.mapper;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.system.dictionary.dto.SysDictionaryDataPageDTO;
import com.geshanzsq.system.dictionary.po.SysDictionaryData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典数据
 *
 * @author geshanzsq
 * @date 2022/6/27
 */
@Mapper
public interface SysDictionaryDataMapper extends BaseMapperPlus<SysDictionaryData> {

    /**
     * 分页
     */
    default PageVO<SysDictionaryData> selectPage(SysDictionaryDataPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<SysDictionaryData>()
                .likeIf(SysDictionaryData::getDictionaryLabel, pageDTO.getDictionaryLabel())
                .eqIf(SysDictionaryData::getStatus, pageDTO.getStatus())
                .eq(SysDictionaryData::getDictionaryId, pageDTO.getDictionaryId())
                .orderByAsc(SysDictionaryData::getSort, SysDictionaryData::getId)
        );
    }

    /**
     * 获取最大排序
     * @param dictionaryId 字典 id
     */
    Integer selectMaxSortByDictionaryId(Long dictionaryId);

}
