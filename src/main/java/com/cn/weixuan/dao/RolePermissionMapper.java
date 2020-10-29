package com.cn.weixuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.weixuan.pojo.RolePermission;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    /**
     * 添加角色权限关系信息
     * @param roleId 角色id
     * @param permissionIds 权限id
     * @return
     */
    int insertRolePermission(Integer roleId, List<Integer> permissionIds);
}
