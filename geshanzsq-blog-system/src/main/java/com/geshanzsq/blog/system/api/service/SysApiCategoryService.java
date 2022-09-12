package com.geshanzsq.blog.system.api.service;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import com.geshanzsq.blog.system.api.dto.SysApiCategoryPageDTO;
import com.geshanzsq.blog.system.api.po.SysApiCategory;
import com.geshanzsq.blog.system.api.vo.SysApiCategoryVO;

import java.util.List;

/**
 * 系统接口分类
 *
 * @author geshanzsq
 * @date 2022/6/24
 */
public interface SysApiCategoryService extends BaseService<SysApiCategory> {

    /**
     * 分页列表
     */
    PageVO<SysApiCategoryVO> pageList(SysApiCategoryPageDTO pageDTO);

    /**
     * 列表排序
     */
    List<SysApiCategoryVO> listSort();

    /**
     * 删除
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();
}
