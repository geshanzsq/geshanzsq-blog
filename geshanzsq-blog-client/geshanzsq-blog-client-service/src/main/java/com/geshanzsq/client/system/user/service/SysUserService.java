package com.geshanzsq.client.system.user.service;

import com.geshanzsq.admin.system.common.po.user.SysUser;
import com.geshanzsq.client.blog.article.vo.BlogArticleAuthorVO;
import com.geshanzsq.common.framework.web.service.BaseService;


/**
 * 系统用户
 *
 * @author geshanzsq
 * @date 2022/9/29
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 获取作者信息
     *
     * @param id 用户 id
     * @return
     */
    BlogArticleAuthorVO getAuthorById(Long id);

}
