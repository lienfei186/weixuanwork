package com.cn.weixuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.weixuan.dao.MenuPermissionMapper;
import com.cn.weixuan.dao.PermissionMapper;
import com.cn.weixuan.pojo.Permission;
import com.cn.weixuan.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;
    @Autowired
    private MenuPermissionMapper menuPermissionMapper;

    /**
     * 此方法为权限信息添加方法
     * @param entity
     * @param menuIds
     * @return
     */
    @Override
    public int savePermission(Permission entity,List<Integer> menuIds) {
        if (entity==null)
            throw new IllegalArgumentException("保存权限不能为空");
        if (menuIds==null||menuIds.size()==0)
            throw new IllegalArgumentException("必须为权限分配资源");
        //保存权限自身信息
        int rows = permissionMapper.insertPermission(entity);
        //保存权限菜单对应关系
        menuPermissionMapper.insertMenuPermission(entity.getPermissionId(),menuIds);
        return rows;
    }

    @Override
    public List<Permission> getRolePermissions(Integer roleId) {
        return permissionMapper.getRolePermissions(roleId);
    }
}
