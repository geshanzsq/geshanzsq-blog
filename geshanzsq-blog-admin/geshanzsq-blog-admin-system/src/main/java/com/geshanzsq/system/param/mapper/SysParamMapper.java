package com.geshanzsq.system.param.mapper;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.system.param.dto.SysParamPageDTO;
import com.geshanzsq.admin.system.common.po.param.SysParam;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数配置
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
@Mapper
public interface SysParamMapper extends BaseMapperPlus<SysParam> {

    /**
     * 分页
     */
    default PageVO<SysParam> selectPage(SysParamPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<SysParam>()
                .likeIf(SysParam::getParamName, pageDTO.getParamName())
                .likeIf(SysParam::getParamKey, pageDTO.getParamKey())
                .likeIf(SysParam::getParamValue, pageDTO.getParamValue())
                .orderByAsc(SysParam::getSort, SysParam::getId)
        );
    }

    /**
     * 获取最大排序
     */
    Integer selectMaxSort();
}
