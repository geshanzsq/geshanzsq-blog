package com.geshanzsq.client.blog.user.service;

import com.geshanzsq.client.common.blog.po.user.BlogThirdUser;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 博客第三方用户
 *
 * @author geshanzsq
 * @date 2022/10/7
 */
public interface BlogThirdUserService extends BaseService<BlogThirdUser> {

    /**
     * 通过第三方平台 uuid 获取数据
     *
     * @param uuid 第三方平台 uuid
     */
    BlogThirdUser getByUuid(String uuid);

}
