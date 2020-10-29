package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.Menu;
import com.cn.weixuan.service.IMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService iMenuService;
    @Autowired
    private Response response;
    //此RequiresPermissions注解用于控制权限基于permission表中的permission字段。
    @RequiresPermissions("user:update")
    //此方法为菜单添加方法
    @RequestMapping("/saveMenu")
    public Response saveMenu(Menu menu){
         int rows = iMenuService.saveMenu(menu);
         if (rows > 0){
             return response.success("菜单添加成功",null);
         }
         return response.failure("菜单添加失败",null);
    }
}
