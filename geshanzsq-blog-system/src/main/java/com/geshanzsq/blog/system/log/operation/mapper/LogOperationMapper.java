package com.geshanzsq.blog.system.log.operation.mapper;

import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.blog.system.log.operation.dto.LogOperationPageDto;
import com.geshanzsq.blog.system.log.operation.po.LogOperation;
import com.geshanzsq.blog.system.log.operation.vo.LogOperationVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作日志
 *
 * @author geshanzsq
 * @date 2022/7/5
 */
@Mapper
public interface LogOperationMapper extends BaseMapperPlus<LogOperation> {

    /**
     * 分页列表
     */
    List<LogOperationVo> pageList(LogOperationPageDto pageDto);
}
