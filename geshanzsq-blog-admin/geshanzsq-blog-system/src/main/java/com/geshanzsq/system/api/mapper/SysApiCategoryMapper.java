package com.geshanzsq.system.api.mapper;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.system.api.dto.SysApiCategoryPageDTO;
import com.geshanzsq.system.api.po.SysApiCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统接口分类
 *
 * @author geshanzsq
 * @date 2022/6/24
 */
@Mapper
public interface SysApiCategoryMapper extends BaseMapperPlus<SysApiCategory> {

    /**
     * 分页
     */
    default PageVO<SysApiCategory> selectPage(SysApiCategoryPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<SysApiCategory>()
                .likeIf(SysApiCategory::getCategoryName, pageDTO.getCategoryName())
                .eqIf(SysApiCategory::getStatus, pageDTO.getStatus())
                .orderByAsc(SysApiCategory::getSort, SysApiCategory::getId)
        );
    }

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}