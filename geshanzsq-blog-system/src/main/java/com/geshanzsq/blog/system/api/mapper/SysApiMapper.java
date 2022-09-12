package com.geshanzsq.blog.system.api.mapper;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.blog.system.api.dto.SysApiPageDTO;
import com.geshanzsq.blog.system.api.po.SysApi;
import com.geshanzsq.blog.system.api.vo.SysApiVO;
import com.geshanzsq.blog.system.menu.vo.SysMenuAuthApiVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 接口
 *
 * @author geshanzsq
 * @date 2022/6/12
 */
@Mapper
public interface SysApiMapper extends BaseMapperPlus<SysApi> {

    /**
     * 分页
     */
    default PageVO<SysApi> selectPage(SysApiPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<SysApi>()
                .likeIf(SysApi::getApiName, pageDTO.getApiName())
                .likeIf(SysApi::getApiUrl, pageDTO.getApiUrl())
                .eqIf(SysApi::getStatus, pageDTO.getStatus())
                .eqIf(SysApi::getApiMethod, pageDTO.getApiMethod())
                .eq(SysApi::getApiCategoryId, pageDTO.getApiCategoryId())
                .orderByAsc(SysApi::getSort, SysApi::getId)
        );
    }

    /**
     * 通过菜单 id 列表获取接口
     * @param menuIds 菜单 id 列表
     * @param status 状态
     * @return
     */
    List<SysApiVO> getApiByMenuIds(@Param("menuIds") List<Long> menuIds,
                                   @Param("status") Integer status);

    /**
     * 获取最大排序
     * @param apiCategoryId 分类 id
     */
    Integer getMaxSortByCategoryId(Long apiCategoryId);

    /**
     * 获取分配的 API 接口
     * @param menuId 菜单 id
     * @return
     */
    List<SysMenuAuthApiVO> selectAuthApiByMenuId(Long menuId);
}
