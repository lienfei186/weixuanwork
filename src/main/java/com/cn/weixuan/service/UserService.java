package com.cn.weixuan.service;

import com.cn.weixuan.pojo.User;

import java.util.List;

/**
 * @author 111111
 */
public interface UserService {

    /***
     * @用户表的查询方法
     *
     *
     */
    public List<User> selectAllUser();
}
