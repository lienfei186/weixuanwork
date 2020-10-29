package com.cn.weixuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.weixuan.pojo.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface UserMapper extends BaseMapper<User> {
    //User selectOne(@Param("userName") String userName);

    int regist(User user);

    /**
     * 根据用户电话获取用户id
     * @param phone
     * @return
     */
    User selectUserId(String phone);
    /**
     * 查询个人信息中的用户信息
     * @param userId
     * @return
     */
    User findByUserList(Integer userId);
    /**
     * 根据用户id查询用户的头像信息
     * @param userId
     * @return
     */
    String selectprofilePath(Integer userId);
    /**
     * 修改头像路径名
     * @param newProfileName 路径名
     * @param userId 用户id
     * @return
     */
    int updateUserProfilePath(String newProfileName, Integer userId);

    /**
     * 根据用户id修改用户个人介绍
     * @param userId
     * @return
     */
    int updateUserPersonal(String personal,Integer userId);

    /**
     * 根据用户id查询用户介绍
     * @param userId
     * @return
     */
    String selectUserPersonal(Integer userId);

    /**
     * 根据用户id查找用户姓名
     * @param userId
     * @return
     */
    String getUserName(Integer userId);
}
