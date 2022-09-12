package com.geshanzsq.blog.common.framework.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geshanzsq.blog.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.util.MybatisUtils;
import org.apache.ibatis.annotations.Param;

/**
 * 在 MyBatis Plus 的 BaseMapper 的基础上拓展，提供更多功能
 *
 * @author geshanzsq
 * @date 2022/6/26
 */
public interface BaseMapperPlus<T> extends BaseMapper<T> {

    /**
     * 分页查询
     */
    default PageVO<T> selectPage(PageDTO pageDTO, @Param("ew") Wrapper<T> queryWrapper) {
        // MyBatis Plus 分页查询
        IPage<T> myBatisPage = MybatisUtils.buildPage(pageDTO);
        selectPage(myBatisPage, queryWrapper);
        // 转换返回值
        return new PageVO<>(myBatisPage.getRecords(), myBatisPage.getTotal());
    }

}
