package com.cn.weixuan.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.weixuan.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 添加权限信息
     * @param entity
     * @return
     */
    int insertPermission(Permission entity);
    List<Permission> getRolePermissions(@Param("roleId") Integer roleId);
}
