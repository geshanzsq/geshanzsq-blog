package com.geshanzsq.admin.blog.friendship.controller;


import com.geshanzsq.admin.blog.friendship.dto.BlogFriendshipLinkAddDTO;
import com.geshanzsq.admin.blog.friendship.dto.BlogFriendshipLinkPageDTO;
import com.geshanzsq.admin.blog.friendship.dto.BlogFriendshipLinkUpdateDTO;
import com.geshanzsq.admin.blog.friendship.mapstruct.BlogFriendshipLinkConverter;
import com.geshanzsq.admin.blog.friendship.service.BlogFriendshipLinkService;
import com.geshanzsq.admin.blog.friendship.vo.BlogFriendshipLinkVO;
import com.geshanzsq.client.common.blog.po.friendship.BlogFriendshipLink;
import com.geshanzsq.common.core.web.response.ResponseResult;
import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.web.controller.BaseController;
import com.geshanzsq.common.log.annotation.Log;
import com.geshanzsq.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Api(tags = "友情链接")
@RestController
@RequestMapping("/blog/friendship")
public class BlogFriendshipLinkController extends BaseController {

    @Autowired
    private BlogFriendshipLinkService blogFriendshipLinkService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    public ResponseResult<PageVO<BlogFriendshipLinkVO>> page(BlogFriendshipLinkPageDTO pageDTO) {
        pageDTO.setOrderColumn("sort,id");
        PageVO<BlogFriendshipLink> pageVO = blogFriendshipLinkService.page(pageDTO);
        return ResponseResult.success(BlogFriendshipLinkConverter.INSTANCE.convert(pageVO));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("详情")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<BlogFriendshipLinkVO> getById(@PathVariable Long id) {
        return ResponseResult.success(BlogFriendshipLinkConverter.INSTANCE.convert(blogFriendshipLinkService.getById(id)));
    }

    @PostMapping()
    @ApiOperation("新增")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "友情链接", businessType = BusinessType.ADD)
    public ResponseResult add(@Valid @RequestBody BlogFriendshipLinkAddDTO addDTO) {
        BlogFriendshipLink blogFriendshipLink = BlogFriendshipLinkConverter.INSTANCE.convert(addDTO);
        blogFriendshipLinkService.save(blogFriendshipLink);
        return ResponseResult.success();
    }

    @PutMapping()
    @ApiOperation("修改")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "友情链接", businessType = BusinessType.UPDATE)
    public ResponseResult update(@Valid @RequestBody BlogFriendshipLinkUpdateDTO updateDTO) {
        BlogFriendshipLink blogFriendshipLink = BlogFriendshipLinkConverter.INSTANCE.convert(updateDTO);
        blogFriendshipLinkService.updateById(blogFriendshipLink);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "友情链接", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        blogFriendshipLinkService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

    @GetMapping("/getMaxSort")
    @ApiOperation("获取最大排序")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<Integer> getMaxSort() {
        Integer maxSort = blogFriendshipLinkService.getMaxSort();
        return ResponseResult.success(maxSort);
    }

}
