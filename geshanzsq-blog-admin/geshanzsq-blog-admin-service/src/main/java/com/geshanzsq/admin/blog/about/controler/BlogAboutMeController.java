package com.geshanzsq.admin.blog.about.controler;


import com.geshanzsq.admin.blog.about.BlogAboutMeEditDTO;
import com.geshanzsq.admin.blog.about.service.BlogAboutMeService;
import com.geshanzsq.admin.blog.about.vo.BlogAboutMeVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 关于我
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Api(tags = "关于我")
@RestController
@RequestMapping("/blog/about")
public class BlogAboutMeController extends BaseController {

    @Autowired
    private BlogAboutMeService blogAboutMeService;

    @ApiOperation("获取内容")
    @GetMapping("/getContent")
    public ResponseResult<BlogAboutMeVO> getContent() {
        BlogAboutMeVO blogAboutMeVO = blogAboutMeService.getContent();
        return ResponseResult.success(blogAboutMeVO);
    }

    @ApiOperation("编辑")
    @PostMapping("/edit")
    public ResponseResult<BlogAboutMeVO> edit(@Valid @RequestBody BlogAboutMeEditDTO editDTO) {
        Long id = blogAboutMeService.edit(editDTO);
        BlogAboutMeVO blogAboutMeVO = new BlogAboutMeVO();
        blogAboutMeVO.setId(id);
        return ResponseResult.success(blogAboutMeVO);
    }

}
