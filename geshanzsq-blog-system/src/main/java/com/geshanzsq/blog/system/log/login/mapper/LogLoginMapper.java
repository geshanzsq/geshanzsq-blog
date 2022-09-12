package com.geshanzsq.blog.system.log.login.mapper;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.blog.system.log.login.dto.LogLoginPageDTO;
import com.geshanzsq.blog.system.log.login.po.LogLogin;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author geshanzsq
 * @date 2022/7/3
 */
@Mapper
public interface LogLoginMapper extends BaseMapperPlus<LogLogin> {

    /**
     * 分页
     */
    default PageVO<LogLogin> selectPage(LogLoginPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<LogLogin>()
                .likeIf(LogLogin::getUsername, pageDTO.getUsername())
                .likeIf(LogLogin::getIpAddress, pageDTO.getIpAddress())
                .leIf(LogLogin::getGmtLogin, pageDTO.getStartGmtLogin())
                .geIf(LogLogin::getGmtLogin, pageDTO.getEndGmtLogin())
                .eqIf(LogLogin::getStatus, pageDTO.getStatus())
                .orderByDesc(LogLogin::getGmtLogin)
        );
    }

}
