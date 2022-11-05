package com.geshanzsq.system.api.mapper;

import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
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
     * 获取最大排序
     */
    Integer selectMaxSort();
}
