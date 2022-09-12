package com.geshanzsq.blog.common.framework.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.blog.common.framework.web.service.BaseService;

/**
 * 在 MyBatis Plus 的 ServiceImpl 的基础上拓展，后续提供更多功能
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public class BaseServiceImpl<M extends BaseMapperPlus<T>, T> extends ServiceImpl<M,T> implements BaseService<T> {
}
