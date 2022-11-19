package com.geshanzsq.client.blog.about.controler;

import com.geshanzsq.client.blog.about.service.BlogAboutMeService;
import com.geshanzsq.client.blog.about.vo.BlogAboutMeVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关于我
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Api(tags = "关于我")
@RestController
@RequestMapping("/about")
public class BlogAboutMeController extends BaseController {

    @Autowired
    private BlogAboutMeService blogAboutMeService;

    @ApiOperation("获取内容")
    @GetMapping("/getContent")
    public ResponseResult<BlogAboutMeVO> getContent() {
        BlogAboutMeVO blogAboutMeVO = blogAboutMeService.getContent();
        return ResponseResult.success(blogAboutMeVO);
    }

}
