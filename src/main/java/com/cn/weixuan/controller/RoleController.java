package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.Role;
import com.cn.weixuan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    public Response response;

    /**
     * 此方法为角色添加方法
     * @param role
     * @param permissionIds
     * @return
     */
    @RequestMapping("/saveRole")
    public Response saveRole(Role role, List<Integer> permissionIds){
        int rows = iRoleService.saveRole(role,permissionIds);
        if (rows > 0){
            return response.failure("添加角色成功",null);
        }
        return response.failure("添加角色失败",null);
    }
}

