package com.geshanzsq.client.blog.board.service.impl;

import com.geshanzsq.client.blog.board.service.BoardService;
import com.geshanzsq.client.system.param.constant.SysParamConstant;
import com.geshanzsq.client.system.param.service.SysParamService;
import com.geshanzsq.common.core.util.string.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 留言板
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SysParamService sysParamService;

    /**
     * 获取开启状态
     */
    @Override
    public boolean getOpenStatus() {
        String paramValue = sysParamService.getParamValueByKey(SysParamConstant.BOARD_OPEN_STATUS);
        if (StrUtils.isBlank(paramValue)) {
            return false;
        }
        return Boolean.valueOf(paramValue);
    }
}
