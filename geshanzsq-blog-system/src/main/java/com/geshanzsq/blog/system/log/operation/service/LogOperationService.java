package com.geshanzsq.blog.system.log.operation.service;

import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.service.BaseService;
import com.geshanzsq.blog.system.log.operation.dto.LogOperationPageDto;
import com.geshanzsq.blog.system.log.operation.po.LogOperation;
import com.geshanzsq.blog.system.log.operation.vo.LogOperationVo;

/**
 * 操作日志
 *
 * @author geshanzsq
 * @date 2022/7/5
 */
public interface LogOperationService extends BaseService<LogOperation> {

    /**
     * 分页列表
     */
    PageVO<LogOperationVo> pageList(LogOperationPageDto pageDto);

    /**
     * 获取详情
     */
    LogOperationVo getLogOperationById(Long id);
}
