package com.geshanzsq.blog.system.api.service;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import com.geshanzsq.blog.system.api.dto.SysApiPageDTO;
import com.geshanzsq.blog.system.api.po.SysApi;
import com.geshanzsq.blog.system.api.vo.SysApiVO;
import com.geshanzsq.blog.system.menu.vo.SysMenuAuthApiVO;

import java.util.List;

/**
 * 接口
 *
 * @author geshanzsq
 * @date 2022/6/12
 */
public interface SysApiService extends BaseService<SysApi> {

    /**
     * 通过菜单 id 列表获取接口
     * @param menuIds 菜单 id 列表
     * @return
     */
    List<SysApiVO> getApiByMenuIds(List<Long> menuIds);

    /**
     * 分页列表
     */
    PageVO<SysApiVO> pageList(SysApiPageDTO pageDTO);

    /**
     * 删除
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     * @param apiCategoryId 分类 id
     */
    Integer getMaxSortByCategoryId(Long apiCategoryId);

    /**
     * 分配 API
     * @param menuId 菜单 id
     * @param apiIds 接口 ids
     */
    void authApi(Long menuId, List<Long> apiIds);

    /**
     * 获取分配的 API 接口
     * @param menuId 菜单 id
     * @return
     */
    List<SysMenuAuthApiVO> getAuthApiByMenuId(Long menuId);
}
