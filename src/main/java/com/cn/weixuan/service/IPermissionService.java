package com.cn.weixuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.weixuan.pojo.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface IPermissionService extends IService<Permission> {
    int savePermission(Permission entity,List<Integer> menuIds);
    List<Permission> getRolePermissions(Integer roleId);
}
