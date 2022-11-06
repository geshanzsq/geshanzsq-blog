package com.geshanzsq.admin.system.user.mapper;

import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.admin.system.user.po.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户角色
 *
 * @author geshanzsq
 * @date 2022/7/17
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapperPlus<SysUserRole> {

    /**
     * 获取关联未删除的用户数量
     * @param roleIds 角色 id 集合
     * @param delFlag 删除标识
     * @return
     */
    Long selectAssociateNotDeleteUserCount(@Param("roleIds") List<Long> roleIds,
                                              @Param("delFlag") Integer delFlag);
}
