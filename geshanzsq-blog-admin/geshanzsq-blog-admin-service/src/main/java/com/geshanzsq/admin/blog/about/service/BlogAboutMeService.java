package com.geshanzsq.admin.blog.about.service;

import com.geshanzsq.admin.blog.about.BlogAboutMeEditDTO;
import com.geshanzsq.admin.blog.about.vo.BlogAboutMeVO;
import com.geshanzsq.client.common.blog.po.about.BlogAboutMe;
import com.geshanzsq.common.framework.web.service.BaseService;

/**
 * 关于我
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
public interface BlogAboutMeService extends BaseService<BlogAboutMe> {

    /**
     * 获取内容
     */
    BlogAboutMeVO getContent();

    /**
     * 编辑
     *
     * @return 关于 id
     */
    Long edit(BlogAboutMeEditDTO editDTO);
}
