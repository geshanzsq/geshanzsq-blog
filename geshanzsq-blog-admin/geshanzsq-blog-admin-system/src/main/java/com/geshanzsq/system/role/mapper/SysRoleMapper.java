package com.geshanzsq.system.role.mapper;

import com.geshanzsq.common.framework.mybatis.page.vo.PageVO;
import com.geshanzsq.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.geshanzsq.common.framework.web.mapper.BaseMapperPlus;
import com.geshanzsq.system.role.dto.SysRolePageDTO;
import com.geshanzsq.system.role.po.SysRole;
import com.geshanzsq.system.role.vo.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色
 *
 * @author geshanzsq
 * @date 2022/3/26
 */
@Mapper
public interface SysRoleMapper extends BaseMapperPlus<SysRole> {

    /**
     * 分页
     */
    default PageVO<SysRole> selectPage(SysRolePageDTO pageDTO) {
        return selectPage(pageDTO, new LambdaQueryWrapperPlus<SysRole>()
                .likeIf(SysRole::getRoleName, pageDTO.getRoleName())
                .likeIf(SysRole::getRoleCode, pageDTO.getRoleCode())
                .eqIf(SysRole::getStatus, pageDTO.getStatus())
                .orderByAsc(SysRole::getSort, SysRole::getId)
        );
    }

    /**
     * 通过用户 id 获取角色
     * @param userId 用户 id
     * @param status 角色状态
     */
    List<SysRoleVO> getRoleByUserIds(@Param("userIds") List<Long> userId, @Param("status") Integer status);

    /**
     * 获取最大排序
     */
    Integer selectMaxSort();
}
