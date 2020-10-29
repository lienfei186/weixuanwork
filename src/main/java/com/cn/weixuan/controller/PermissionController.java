package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.Permission;
import com.cn.weixuan.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;
    @Autowired
    private Response response;

    /**
     * 此方法为权限添加方法
     * @param entity
     * @param menuIds
     * @return
     */
    @RequestMapping("/savePermission")
    public Response savePermission(Permission entity, List<Integer> menuIds){
        int rows = iPermissionService.savePermission(entity,menuIds);
        if (rows > 0){
            return response.success("权限添加成功",null);
        }
        return response.failure("权限添加失败",null);
    }
}
