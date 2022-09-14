package com.geshanzsq.common.framework.mybatis.plugin.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.geshanzsq.common.core.util.string.StrUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 扩展 LambdaQueryWrapper 类
 * 添加 xxxIf 方法，当值不存在的时候，不要拼接条件
 *
 * @author geshanzsq
 * @date 2022/3/27
 */
public class LambdaQueryWrapperPlus<T> extends LambdaQueryWrapper<T> {

    /**
     * 模糊查询
     */
    public LambdaQueryWrapperPlus<T> likeIf(SFunction<T, ?> column, String value) {
        if (StrUtils.isNotBlank(value)) {
            return (LambdaQueryWrapperPlus<T>) super.like(column, value);
        }
        return this;
    }

    /**
     * 相等
     */
    public LambdaQueryWrapperPlus<T> eqIf(SFunction<T, ?> column, Object value) {
        if (value == null) {
            return this;
        }
        if (value instanceof String && StrUtils.isBlank(String.valueOf(value))) {
            return this;
        }
        return (LambdaQueryWrapperPlus<T>) super.eq(column, value);
    }

    /**
     * 包含
     */
    public LambdaQueryWrapperPlus<T> inIf(SFunction<T, ?> column, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return this;
        }
        return (LambdaQueryWrapperPlus<T>) super.in(column, values);
    }

    /**
     * 不包含
     */
    public LambdaQueryWrapperPlus<T> notInIf(SFunction<T, ?> column, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return this;
        }
        return (LambdaQueryWrapperPlus<T>) super.notIn(column, values);
    }

    /**
     * 小于
     */
    public LambdaQueryWrapperPlus<T> ltIf(SFunction<T, ?> column, Object value) {
        if (value != null) {
            return (LambdaQueryWrapperPlus<T>) super.lt(column, value);
        }
        return this;
    }

    /**
     * 小于等于
     */
    public LambdaQueryWrapperPlus<T> leIf(SFunction<T, ?> column, Object value) {
        if (value != null) {
            return (LambdaQueryWrapperPlus<T>) super.le(column, value);
        }
        return this;
    }

    /**
     * 大于
     */
    public LambdaQueryWrapperPlus<T> gtIf(SFunction<T, ?> column, Object value) {
        if (value != null) {
            return (LambdaQueryWrapperPlus<T>) super.gt(column, value);
        }
        return this;
    }

    /**
     * 大于等于
     */
    public LambdaQueryWrapperPlus<T> geIf(SFunction<T, ?> column, Object value) {
        if (value != null) {
            return (LambdaQueryWrapperPlus<T>) super.ge(column, value);
        }
        return this;
    }

}
