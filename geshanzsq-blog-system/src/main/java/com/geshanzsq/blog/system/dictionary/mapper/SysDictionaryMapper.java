package com.geshanzsq.blog.system.dictionary.mapper;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.blog.system.dictionary.dto.SysDictionaryPageDTO;
import com.geshanzsq.blog.system.dictionary.po.SysDictionary;
import com.geshanzsq.blog.system.dictionary.vo.DictionaryInfoVO;
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
     * 分页
     */
    default PageVO<SysDictionary> selectPage(SysDictionaryPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<SysDictionary>()
                .likeIf(SysDictionary::getDictionaryName, pageDTO.getDictionaryName())
                .likeIf(SysDictionary::getDictionaryCode, pageDTO.getDictionaryCode())
                .eqIf(SysDictionary::getStatus, pageDTO.getStatus())
                .orderByAsc(SysDictionary::getSort, SysDictionary::getId)
        );
    }

    /**
     * 获取最大排序
     */
    Integer getMaxSort();

    /**
     * 获取所有字典详细信息
     * @param status 状态
     */
    List<DictionaryInfoVO> getAllDictionaryInfo(Integer status);

}
