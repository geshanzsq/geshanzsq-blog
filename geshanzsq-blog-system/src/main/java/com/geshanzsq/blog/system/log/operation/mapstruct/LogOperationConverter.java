package com.geshanzsq.blog.system.log.operation.mapstruct;

import com.geshanzsq.blog.common.log.dto.LogDTO;
import com.geshanzsq.blog.system.log.operation.po.LogOperation;
import com.geshanzsq.blog.system.log.operation.vo.LogOperationVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 操作日志转换
 *
 * @author geshanzsq
 * @date 2022/7/9
 */
@Mapper
public interface LogOperationConverter {

    LogOperationConverter INSTANCE = Mappers.getMapper(LogOperationConverter.class);

    LogOperationVo convert(LogOperation logOperation);

    LogOperation convert(LogDTO logDto);

}
