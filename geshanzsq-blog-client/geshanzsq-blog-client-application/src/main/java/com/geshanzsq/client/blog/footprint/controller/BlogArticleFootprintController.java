package com.geshanzsq.client.blog.footprint.controller;

import com.geshanzsq.client.blog.article.vo.BlogArticlePageVO;
import com.geshanzsq.client.blog.footprint.service.BlogArticleFootprintService;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.mybatis.page.dto.PageDTO;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章足迹
 *
 * @author geshanzsq
 * @date 2022/10/15
 */
@Api(tags = "文章足迹")
@RestController
@RequestMapping("/user/footprint")
public class BlogArticleFootprintController extends BaseController {

    @Autowired
    private BlogArticleFootprintService blogArticleFootprintService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    public ResponseResult<PageVO<BlogArticlePageVO>> page(PageDTO pageDTO) {
        PageVO<BlogArticlePageVO> pageVO = blogArticleFootprintService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

}
