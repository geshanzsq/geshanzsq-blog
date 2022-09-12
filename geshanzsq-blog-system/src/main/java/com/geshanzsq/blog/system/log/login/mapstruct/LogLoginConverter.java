package com.geshanzsq.blog.system.log.login.mapstruct;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.system.log.login.dto.LogLoginAddDTO;
import com.geshanzsq.blog.system.log.login.po.LogLogin;
import com.geshanzsq.blog.system.log.login.vo.LogLoginVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 登录日志对象转换
 *
 * @author geshanzsq
 * @date 2022/7/3
 */
@Mapper
public interface LogLoginConverter {

    LogLoginConverter INSTANCE = Mappers.getMapper(LogLoginConverter.class);

    LogLoginVO convert(LogLogin logLogin);

    PageVO<LogLoginVO> convert(PageVO<LogLogin> pageVo);

    LogLogin convert(LogLoginAddDTO addDTO);

}
