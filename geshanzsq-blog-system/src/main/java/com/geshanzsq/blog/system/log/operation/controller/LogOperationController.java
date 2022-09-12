package com.geshanzsq.blog.system.log.operation.controller;

import com.geshanzsq.blog.common.core.web.response.ResponseResult;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.web.controller.BaseController;
import com.geshanzsq.blog.system.log.operation.dto.LogOperationPageDto;
import com.geshanzsq.blog.system.log.operation.service.LogOperationService;
import com.geshanzsq.blog.system.log.operation.vo.LogOperationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志
 *
 * @author geshanzsq
 * @date 2022/7/9
 */
@Api(tags = "操作日志")
@RestController
@RequestMapping("/system/log/operation")
public class LogOperationController extends BaseController {

    @Autowired
    private LogOperationService logOperationService;

    @GetMapping("/page")
    @ApiOperation(("分页列表"))
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<LogOperationVo>> page(LogOperationPageDto pageDto) {
        PageVO<LogOperationVo> pageVo = logOperationService.pageList(pageDto);
        return ResponseResult.success(pageVo);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<LogOperationVo> getById(@PathVariable Long id) {
        LogOperationVo logOperationVo = logOperationService.getLogOperationById(id);
        return ResponseResult.success(logOperationVo);
    }
}
