package com.geshanzsq.common.framework.web.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.QueryWrapperPlus;

import java.util.List;

/**
 * 在 MyBatis Plus 的 IService 的基础上拓展，提供更多功能
 *
 * @author geshanzsq
 * @date 2022/8/16
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 查询分页
     *
     * @param d             实体类参数对接
     * @param selectColumns 查询返回的列
     */
    <D> PageVO<T> page(D d, SFunction<T, ?>... selectColumns);

    /**
     * 查询列表
     *
     * @param d             实体类参数对接
     * @param selectColumns 查询返回的列
     */
    default <D> List<T> list(D d, SFunction<T, ?>... selectColumns) {
        return list(new QueryWrapperPlus<T>().buildQueryWrapper(getEntityClass(), d, selectColumns));
    }

    /**
     * 查询单条
     *
     * @param d 实体类参数对接
     */
    default <D> T getOne(D d) {
        return getOne(new QueryWrapperPlus<T>().buildQueryWrapper(getEntityClass(), d));
    }

    /**
     * 查询单条
     *
     * @param d       实体类参数对接
     * @param throwEx 有多个结果是否抛出异常
     */
    default <D> T getOne(D d, boolean throwEx) {
        return getOne(new QueryWrapperPlus<T>().buildQueryWrapper(getEntityClass(), d), throwEx);
    }

    /**
     * 查询总记录数
     *
     * @param d 实体类参数对接
     */
    default <D> Long count(D d) {
        return count(new QueryWrapperPlus<T>().buildQueryWrapper(getEntityClass(), d));
    }

}
