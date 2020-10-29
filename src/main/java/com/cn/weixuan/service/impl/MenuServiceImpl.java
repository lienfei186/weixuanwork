package com.cn.weixuan.service.impl;

import com.cn.weixuan.dao.MenuMapper;
import com.cn.weixuan.pojo.Menu;
import com.cn.weixuan.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int saveMenu(Menu menu) {
        int rows = menuMapper.saveMenu(menu);
        return rows;
    }
}
