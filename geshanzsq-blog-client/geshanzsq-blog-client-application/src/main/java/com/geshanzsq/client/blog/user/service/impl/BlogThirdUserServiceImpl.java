package com.geshanzsq.client.blog.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.geshanzsq.client.blog.user.mapper.BlogThirdUserMapper;
import com.geshanzsq.client.blog.user.service.BlogThirdUserService;
import com.geshanzsq.client.common.blog.po.user.BlogThirdUser;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 博客第三方用户
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
@Service
public class BlogThirdUserServiceImpl extends BaseServiceImpl<BlogThirdUserMapper, BlogThirdUser> implements BlogThirdUserService {

    @Autowired
    private BlogThirdUserMapper blogThirdUserMapper;

    /**
     * 通过第三方平台 uuid 获取数据
     *
     * @param uuid 第三方平台 uuid
     */
    @Override
    public BlogThirdUser getByUuid(String uuid) {
        LambdaQueryWrapper<BlogThirdUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogThirdUser::getUuid, uuid);
        return blogThirdUserMapper.selectOne(wrapper);
    }
}
