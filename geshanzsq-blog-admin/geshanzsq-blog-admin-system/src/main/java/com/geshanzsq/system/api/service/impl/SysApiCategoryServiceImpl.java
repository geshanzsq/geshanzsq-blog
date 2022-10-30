package com.geshanzsq.system.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.common.core.exception.ParamException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.system.api.dto.SysApiCategoryPageDTO;
import com.geshanzsq.system.api.mapper.SysApiCategoryMapper;
import com.geshanzsq.system.api.mapper.SysApiMapper;
import com.geshanzsq.system.api.mapstruct.SysApiCategoryConverter;
import com.geshanzsq.system.api.po.SysApi;
import com.geshanzsq.system.api.po.SysApiCategory;
import com.geshanzsq.system.api.service.SysApiCategoryService;
import com.geshanzsq.system.api.vo.SysApiCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 系统接口分类
 *
 * @author geshanzsq
 * @date 2022/6/24
 */
@Service
public class SysApiCategoryServiceImpl extends BaseServiceImpl<SysApiCategoryMapper, SysApiCategory> implements SysApiCategoryService {

    @Autowired
    private SysApiCategoryMapper sysApiCategoryMapper;
    @Autowired
    private SysApiMapper sysApiMapper;

    /**
     * 分页列表
     */
    @Override
    public PageVO<SysApiCategoryVO> pageList(SysApiCategoryPageDTO pageDTO) {
        PageVO<SysApiCategory> page = sysApiCategoryMapper.selectPage(pageDTO);
        return SysApiCategoryConverter.INSTANCE.convert(page);
    }

    /**
     * 列表排序
     */
    @Override
    public List<SysApiCategoryVO> listSort() {
        LambdaQueryWrapper<SysApiCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SysApiCategory::getSort, SysApiCategory::getId);
        return SysApiCategoryConverter.INSTANCE.convertList(sysApiCategoryMapper.selectList(wrapper));
    }

    /**
     * 删除
     */
    @Override
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        // 判断是否关联接口，如果已关联，不允许删除
        LambdaQueryWrapper<SysApi> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysApi::getApiCategoryId, ids);
        if (sysApiMapper.selectCount(wrapper) > 0) {
            throw new ParamException(MessageUtils.message("api.category.allocated"));
        }
        // 删除数据
        sysApiCategoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 获取最大排序
     */
    @Override
    public Integer getMaxSort() {
        return sysApiCategoryMapper.selectMaxSort();
    }
}
