package com.geshanzsq.blog.system.user.mapper;

import com.geshanzsq.blog.common.core.enums.DelFlag;
import com.geshanzsq.blog.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.blog.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.blog.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.blog.system.user.dto.SysUserPageDTO;
import com.geshanzsq.blog.system.user.po.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author geshanzsq
 * @date 2022/3/20
 */
@Mapper
public interface SysUserMapper extends BaseMapperPlus<SysUser> {

    /**
     * 分页
     */
    default PageVO<SysUser> selectPage(SysUserPageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<SysUser>()
                .likeIf(SysUser::getUsername, pageDTO.getUsername())
                .likeIf(SysUser::getNickName, pageDTO.getNickName())
                .eqIf(SysUser::getStatus, pageDTO.getStatus())
                .eqIf(SysUser::getUserType, pageDTO.getUserType())
                .geIf(SysUser::getGmtCreate, pageDTO.getBeginGmtCreate())
                .leIf(SysUser::getGmtCreate, pageDTO.getEndGmtCreate())
                .inIf(SysUser::getId, pageDTO.getIds())
                .notInIf(SysUser::getId, pageDTO.getNotIds())
                .orderBy(false, true, SysUser::getGmtCreate, SysUser::getId)
        );
    }

}
