package com.geshanzsq.blog.system.param.mapper;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.blog.system.param.dto.SysParamPageDTO;
import com.geshanzsq.blog.system.param.po.SysParam;
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
    Integer getMaxSort();
}
