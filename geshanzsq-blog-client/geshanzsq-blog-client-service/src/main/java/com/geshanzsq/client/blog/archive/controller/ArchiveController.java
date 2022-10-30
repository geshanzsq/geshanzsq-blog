package com.geshanzsq.client.blog.archive.controller;

import com.geshanzsq.client.blog.article.service.BlogArticleService;
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
 * 归档
 *
 * @author geshanzsq
 * @date 2022/9/27
 */
@Api(tags = "归档")
@RestController
@RequestMapping("/archive")
public class ArchiveController extends BaseController {

    @Autowired
    private BlogArticleService blogArticleService;


    @ApiOperation("发布时间线")
    @GetMapping("/getGmtPublishLine")
    public ResponseResult<List<String>> getGmtPublishLine() {
        List<String> list = blogArticleService.getArchiveGmtPublishLine();
        return ResponseResult.success(list);
    }

}
