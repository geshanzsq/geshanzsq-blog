package com.geshanzsq.blog.system.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.blog.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.blog.system.api.mapper.SysApiMenuMapper;
import com.geshanzsq.blog.system.api.po.SysApiMenu;
import com.geshanzsq.blog.system.api.service.SysApiMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口菜单
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
@Service
public class SysApiMenuServiceImpl extends BaseServiceImpl<SysApiMenuMapper, SysApiMenu> implements SysApiMenuService {
}
