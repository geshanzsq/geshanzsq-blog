package com.geshanzsq.blog.common.framework.mybatis.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geshanzsq.blog.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.blog.common.framework.mybatis.property.PageProperty;

/**
 * MyBatis 工具类
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
public class MybatisUtils {

    /**
     * 构建分页
     */
    public static <T> Page<T> buildPage(PageDTO pageDTO) {
        Long pageNum = 1L;
        Long pageSize = PageProperty.defaultPageSize;
        if (pageDTO != null) {
            pageNum = pageDTO.getPageNum() == null ? 1L : pageDTO.getPageNum();
            pageSize = pageDTO.getPageSize() == null ? PageProperty.defaultPageSize : pageDTO.getPageSize();
            // 如果超过最大分页数，则设置为最大分页数
            if (pageSize > PageProperty.maxPageSize) {
                pageSize = PageProperty.maxPageSize;
            }
        }
        Page<T> page = new Page<>(pageNum, pageSize);
        return page;
    }

}
