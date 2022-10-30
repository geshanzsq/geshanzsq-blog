package com.geshanzsq.client.blog.friendship.controller;

import com.geshanzsq.client.blog.friendship.service.BlogFriendshipLinkService;
import com.geshanzsq.client.blog.friendship.vo.BlogFriendshipLinkListVO;
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
 * 友情链接
 *
 * @author geshanzsq
 * @date 2022/9/24
 */
@Api(tags = "友情链接")
@RestController
@RequestMapping("/friendship")
public class BlogFriendshipLinkController extends BaseController {

    @Autowired
    private BlogFriendshipLinkService blogFriendshipLinkService;

    @ApiOperation("列表")
    @GetMapping("/list")
    public ResponseResult<List<BlogFriendshipLinkListVO>> list() {
        List<BlogFriendshipLinkListVO> list = blogFriendshipLinkService.listLink();
        return ResponseResult.success(list);
    }

}
