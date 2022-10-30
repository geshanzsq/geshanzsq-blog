package com.geshanzsq.client.blog.category.controller;

import com.geshanzsq.client.blog.category.service.BlogCategoryService;
import com.geshanzsq.client.blog.category.vo.BlogCategoryListVO;
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
 * 分类
 *
 * @author geshanzsq
 * @date 2022/9/18
 */
@Api(tags = "分类")
@RestController
@RequestMapping("/category")
public class BlogCategoryController extends BaseController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @ApiOperation("列表")
    @GetMapping("list")
    public ResponseResult<List<BlogCategoryListVO>> list() {
        List<BlogCategoryListVO> list = blogCategoryService.listCategory();
        return ResponseResult.success(list);
    }

}
