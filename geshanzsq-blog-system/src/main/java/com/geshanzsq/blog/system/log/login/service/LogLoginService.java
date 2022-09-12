package com.geshanzsq.blog.system.log.login.service;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import com.geshanzsq.blog.system.log.login.dto.LogLoginPageDTO;
import com.geshanzsq.blog.system.log.login.po.LogLogin;
import com.geshanzsq.blog.system.log.login.vo.LogLoginVO;

/**
 * 登录日志
 *
 * @author geshanzsq
 * @date 2022/7/3
 */
public interface LogLoginService extends BaseService<LogLogin> {

    /**
     * 分页列表
     */
    PageVO<LogLoginVO> pageList(LogLoginPageDTO pageDTO);

}
