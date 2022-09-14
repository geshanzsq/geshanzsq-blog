package com.geshanzsq.system.log.operation.service.impl;

import com.geshanzsq.common.framework.mybatis.page.util.PageUtils;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.service.impl.BaseServiceImpl;
import com.geshanzsq.system.log.operation.dto.LogOperationPageDto;
import com.geshanzsq.system.log.operation.mapper.LogOperationMapper;
import com.geshanzsq.system.log.operation.mapstruct.LogOperationConverter;
import com.geshanzsq.system.log.operation.po.LogOperation;
import com.geshanzsq.system.log.operation.service.LogOperationService;
import com.geshanzsq.system.log.operation.vo.LogOperationVo;
import com.geshanzsq.system.user.service.SysUserService;
import com.geshanzsq.system.user.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 操作日志
 *
 * @author geshanzsq
 * @date 2022/7/5
 */
@Service
public class LogOperationServiceImpl extends BaseServiceImpl<LogOperationMapper, LogOperation> implements LogOperationService {

    @Autowired
    private LogOperationMapper logOperationMapper;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页列表
     */
    @Override
    public PageVO<LogOperationVo> pageList(LogOperationPageDto pageDto) {
        PageUtils.startPage(pageDto);
        List<LogOperationVo> list = logOperationMapper.pageList(pageDto);
        PageVO<LogOperationVo> pageVo = PageUtils.getPage(list);
        return pageVo;
    }

    /**
     * 获取详情
     */
    @Override
    public LogOperationVo getLogOperationById(Long id) {
        LogOperation logOperation = logOperationMapper.selectById(id);
        if (logOperation == null) {
            return null;
        }
        LogOperationVo logOperationVo = LogOperationConverter.INSTANCE.convert(logOperation);
        // 获取操作人信息
        List<SysUserVO> userList = sysUserService.getUsernameAndNickNameByUserIds(Arrays.asList(logOperationVo.getUserId()));
        if (!CollectionUtils.isEmpty(userList)) {
            SysUserVO sysUserVo = userList.get(0);
            logOperationVo.setUsername(sysUserVo.getUsername());
            logOperationVo.setNickName(sysUserVo.getNickName());
        }
        return logOperationVo;
    }
}
