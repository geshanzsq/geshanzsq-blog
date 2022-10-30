package com.geshanzsq.client.blog.tag.controller;

import com.geshanzsq.client.blog.tag.service.BlogTagService;
import com.geshanzsq.client.blog.tag.vo.BlogTagListVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Api(tags = "标签")
@RestController
@RequestMapping("/tag")
public class BlogTagController extends BaseController {

    @Autowired
    private BlogTagService blogTagService;

    @ApiOperation("列表")
    @GetMapping("/list")
    public ResponseResult<List<BlogTagListVO>> list() {
        List<BlogTagListVO> list = blogTagService.listTag();
        return ResponseResult.success(list);
    }

}
