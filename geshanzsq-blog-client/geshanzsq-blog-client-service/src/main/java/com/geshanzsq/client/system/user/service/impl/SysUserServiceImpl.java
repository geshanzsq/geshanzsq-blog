package com.geshanzsq.client.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.admin.system.common.po.user.SysUser;
import com.geshanzsq.client.blog.article.vo.BlogArticleAuthorVO;
import com.geshanzsq.client.system.user.mapper.SysUserMapper;
import com.geshanzsq.client.system.user.mapstruct.SysUserConverter;
import com.geshanzsq.client.system.user.service.SysUserService;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 获取作者信息
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public BlogArticleAuthorVO getAuthorById(Long id) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getId, id);
        wrapper.select(SysUser::getNickName, SysUser::getAvatar);
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            return null;
        }
        return SysUserConverter.INSTANCE.convertAuthor(sysUser);
    }
}
