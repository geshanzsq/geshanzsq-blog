package com.geshanzsq.admin.blog.recommend.controller;

import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendListDTO;
import com.geshanzsq.admin.blog.recommend.dto.BlogArticleRecommendUpdateSortDTO;
import com.geshanzsq.admin.blog.recommend.service.BlogArticleRecommendService;
import com.geshanzsq.admin.blog.recommend.vo.BlogArticleRecommendVO;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.web.controller.BaseController;
import com.geshanzsq.common.log.annotation.Log;
import com.geshanzsq.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章推荐
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Api(tags = "文章推荐")
@RestController
@RequestMapping("/blog/recommend")
public class BlogArticleRecommendController extends BaseController {

    @Autowired
    private BlogArticleRecommendService blogArticleRecommendService;

    @ApiOperation("列表")
    @GetMapping("/list")
    public ResponseResult<List<BlogArticleRecommendVO>> list(BlogArticleRecommendListDTO listDTO) {
        List<BlogArticleRecommendVO> list = blogArticleRecommendService.list(listDTO);
        return ResponseResult.success(list);
    }

    @ApiOperation("更新排序")
    @PutMapping("/updateSort")
    @Log(moduleName = "文章推荐", businessType = BusinessType.UPDATE)
    public ResponseResult updateSort(@Valid @RequestBody List<BlogArticleRecommendUpdateSortDTO> sortDTOList) {
        blogArticleRecommendService.updateSort(sortDTOList);
        return ResponseResult.success();
    }

}
