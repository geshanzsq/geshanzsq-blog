package com.geshanzsq.common.framework.mybatis.plugin.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.geshanzsq.common.core.exception.ServiceException;
import com.geshanzsq.common.core.util.message.MessageUtils;
import com.geshanzsq.common.core.util.string.StrUtils;
import com.geshanzsq.common.framework.mybatis.plugin.annotation.Query;
import com.geshanzsq.common.framework.mybatis.plugin.constant.FieldConstant;
import com.geshanzsq.common.framework.mybatis.plugin.enums.QueryWay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扩展 QueryWrapper 类，提供更多功能
 *
 * @author geshanzsq
 * @date 2022/11/4
 */
public class QueryWrapperPlus<T> extends QueryWrapper<T> {

    private static final Logger log = LoggerFactory.getLogger(QueryWrapperPlus.class);

    /**
     * 排序字段分割标识
     */
    private static final String ORDER_BY_COLUMN_SPLIT = ",";

    /**
     * 构建 Wrapper 查询条件
     *
     * @param clazz         PO 实体类
     * @param d             DTO 实体参数对象
     * @param selectColumns 查询返回的列
     * @return 查询构造器
     */
    public <D> QueryWrapperPlus<T> buildQueryWrapper(Class<T> clazz, D d, SFunction<T, ?>... selectColumns) {
        QueryWrapperPlus queryWrapper = new QueryWrapperPlus();
        // 构建指定返回的列
        queryWrapper.lambda().select(selectColumns);
        if (d == null) {
            return queryWrapper;
        }

        // 获取 T 属性和列名
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        if (tableInfo == null) {
            log.error("没有找到数据表对应的实体类，当前传入的 Clazz：{}", clazz);
            throw new ServiceException(MessageUtils.message("system.exception"));
        }

        // T 属性和列名对应关系
        Map<String, String> propertyColumnMap = new HashMap<>(tableInfo.getFieldList().size());
        tableInfo.getFieldList().forEach(table -> {
            propertyColumnMap.put(table.getProperty(), table.getColumn());
        });
        // 主键不在 fieldList 里，需单独获取
        if (StrUtils.isNotBlank(tableInfo.getKeyProperty())) {
            propertyColumnMap.put(tableInfo.getKeyProperty(), tableInfo.getKeyColumn());
        }

        // 获取 d 属性
        List<Field> fieldList = ReflectionKit.getFieldList(ClassUtils.getUserClass(d.getClass()));
        for (Field field : fieldList) {
            field.setAccessible(true);
            // 获取查询注解
            Query query = field.getAnnotation(Query.class);

            // 获取查询属性名称
            String fieldName = getQueryFieldName(query, field);
            // 是否忽略查询
            if (query != null && query.ignore()) {
                continue;
            }

            // 校验查询属性是否在 T 实体类
            verifyQueryFieldNameExist(fieldName, propertyColumnMap);

            // 获取查询值
            Object value = getFieldValue(field, d);

            // 如果为空并且不需要空查询（默认不需要空查询），则跳过此属性查询
            if (StrUtils.isNullBlank(value) && (query == null || !query.empty())) {
                continue;
            }

            // 获取查询属性对应的列
            String column = propertyColumnMap.get(fieldName);
            // 构造查询条件
            buildQueryCondition(queryWrapper, query, column, value);
        }

        buildOrderByCondition(queryWrapper, propertyColumnMap, fieldList, d);

        return queryWrapper;
    }

    /**
     * 获取查询属性名称
     */
    private String getQueryFieldName(Query query, Field field) {
        return query != null && StrUtils.isNotBlank(query.fieldName()) ? query.fieldName() : field.getName();
    }

    /**
     * 校验查询属性是否在 T 实体类
     */
    private void verifyQueryFieldNameExist(String fieldName, Map<String, String> propertyColumnMap) {
        if (!propertyColumnMap.containsKey(fieldName)) {
            String message = StrUtils.format("查询条件字段：{fieldName} 不存在", fieldName);
            throw new ServiceException(message);
        }
    }

    /**
     * 获取属性值
     */
    private <D> Object getFieldValue(Field field, D d) {
        if (field == null) {
            return null;
        }
        Object value = null;
        try {
            value = field.get(d);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ServiceException(MessageUtils.message("system.exception"));
        }
        return value;
    }

    /**
     * 构建查询条件
     *
     * @param queryWrapper 查询构造器
     * @param query 查询注解
     * @param column 列名称
     * @param value 值
     */
    private void buildQueryCondition(QueryWrapperPlus queryWrapper, Query query, String column, Object value) {
        // 默认为相等
        if (query == null || query.value() == null) {
            queryWrapper.eq(column, value);
            return;
        }
        // 查询方式
        QueryWay queryWay = query.value();
        switch (queryWay) {
            case EQ: {
                queryWrapper.eq(column, value);
                break;
            }
            case NE: {
                queryWrapper.ne(column, value);
                break;
            }
            case GT: {
                queryWrapper.gt(column, value);
                break;
            }
            case GE: {
                queryWrapper.ge(column, value);
                break;
            }
            case LT: {
                queryWrapper.lt(column, value);
                break;
            }
            case LE: {
                queryWrapper.le(column, value);
                break;
            }
            case LIKE: {
                queryWrapper.like(column, value);
                break;
            }
            case NOT_LIKE: {
                queryWrapper.notLike(column, value);
                break;
            }
            case LIKE_LEFT: {
                queryWrapper.likeLeft(column, value);
                break;
            }
            case LIKE_RIGHT: {
                queryWrapper.likeRight(column, value);
                break;
            }
            case IN: {
                queryWrapper.in(column, value);
                break;
            }
            case NOT_IN: {
                queryWrapper.notIn(column, value);
                break;
            }
            default: {
                queryWrapper.eq(column, value);
            }
        }
    }

    /**
     * 构造排序
     *
     * @param queryWrapper 查询构造器
     * @param propertyColumnMap T 属性和列名
     * @param d 参数
     * @param fieldList d 属性列表
     */
    private <D> void buildOrderByCondition(QueryWrapperPlus queryWrapper, Map<String, String> propertyColumnMap,
                                           List<Field> fieldList, D d) {
        // 排序列
        Field orderColumnField = fieldList.stream().filter(f -> FieldConstant.ORDER_COLUMN.equals(f.getName())).findFirst().orElse(null);
        // 如果排序字段为空，或者不是 String 类型，不进行排序
        Object orderColumnObj = getFieldValue(orderColumnField, d);
        if (!(orderColumnObj instanceof String) || StrUtils.isNullBlank(orderColumnObj)) {
            return;
        }

        // 排序类型
        Field orderTypeField = fieldList.stream().filter(f -> FieldConstant.ORDER_TYPE.equals(f.getName())).findFirst().orElse(null);
        Object orderTypeObj = getFieldValue(orderTypeField, d);

        String[] orderColumns = orderColumnObj.toString().split(ORDER_BY_COLUMN_SPLIT);
        String[] orderTypes = orderTypeObj == null ? new String[] {} : orderTypeObj.toString().split(ORDER_BY_COLUMN_SPLIT);

        // 构造排序
        for (int i = 0; i < orderColumns.length; i++) {
            String orderColumn = orderColumns[i];
            if (StrUtils.isBlank(orderColumn)) {
                continue;
            }
            // 校验排序字段是否存在
            String column = propertyColumnMap.get(orderColumn);
            if (StrUtils.isBlank(column)) {
                String message = StrUtils.format("排序字段：{}，不存在", orderColumn);
                throw new ServiceException(message);
            }

            // 是否升序排序
            boolean isAsc = i < orderTypes.length ? SqlKeyword.ASC.getSqlSegment().equalsIgnoreCase(orderTypes[i]) : true;
            queryWrapper.orderBy(true, isAsc, column);
        }

    }

}
