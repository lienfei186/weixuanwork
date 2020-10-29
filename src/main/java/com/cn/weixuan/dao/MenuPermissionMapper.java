package com.cn.weixuan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuPermissionMapper {
    /**
     * 添加权限菜单对应关系信息
     * @param permissionId 权限id
     * @param menuIds 对应的菜单id
     * @return
     */
    int insertMenuPermission(Integer permissionId, List<Integer> menuIds);
}
