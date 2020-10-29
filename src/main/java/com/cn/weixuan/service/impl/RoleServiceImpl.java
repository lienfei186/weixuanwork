package com.cn.weixuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.weixuan.dao.RoleMapper;
import com.cn.weixuan.dao.RolePermissionMapper;
import com.cn.weixuan.pojo.Role;
import com.cn.weixuan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 添加一个角色信息
     * @param entity
     * @param permissionIds
     * @return
     */
    @Override
    public int saveRole(Role entity, List<Integer> permissionIds) {
        if (entity==null)
            throw new IllegalArgumentException("保存j角色不能为空");
        if (permissionIds==null||permissionIds.size()==0)
            throw new IllegalArgumentException("必须角色分配权限");
        //保存角色自身信息
        int rows = roleMapper.insertRole(entity);
        //保存角色权限对应关系
        rolePermissionMapper.insertRolePermission(entity.getRoleId(),permissionIds);
        return rows;
    }

    @Override
    public List<Role> getUserRoles(Integer userId) {
        return roleMapper.getUserRoles(userId);
    }
}
