package com.geshanzsq.client.blog.board.controller;

import com.geshanzsq.client.blog.board.service.BoardService;
import com.geshanzsq.client.blog.board.vo.BoardOpenStatusVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 留言板
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
@Api(tags = "留言板")
@RestController
@RequestMapping("/board")
public class BoardController extends BaseController {

    @Autowired
    private BoardService boardService;

    @ApiOperation("获取开启状态")
    @GetMapping("/getOpenStatus")
    public ResponseResult<BoardOpenStatusVO> getOpenStatus() {
        boolean openStatus = boardService.getOpenStatus();
        BoardOpenStatusVO boardOpenStatusVO = BoardOpenStatusVO.builder().status(openStatus).build();
        return ResponseResult.success(boardOpenStatusVO);
    }

}
