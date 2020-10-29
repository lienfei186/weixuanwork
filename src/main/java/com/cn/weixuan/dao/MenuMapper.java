package com.cn.weixuan.dao;

import com.cn.weixuan.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    int saveMenu(Menu menu);
}
