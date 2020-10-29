package com.cn.weixuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.weixuan.base.module.CacheUser;
import com.cn.weixuan.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface IUserService extends IService<User> {
    /**
     * 注册
     */
    int regist(User user);

    /**
     * create by: YHY
     *
     * @param userName 用户名
     * @return 用户
     */
    User findByUsername(String userName);

    /**
     * create by: YHY
     *
     * @return 用户
     */
    User findByPhone(String phone);

    /**
     * create by: YHY
     * description: 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    CacheUser login(String userName, String password);


    /**
     * create by: YHY
     * description: 登出
     * create time: 2020/6/28 16:30
     */
    void logout();

    List<User> listUsers();

    /**
     * 获取用户个人信息数据
     * @param phone 用户id
     * @return
     */
    User findByUserList(String phone);

    /**
     * 设置用户头像
     * @param profile 头像
     * @return
     */
    int updUserProfile(MultipartFile profile);

    /**
     * 修改用户个人介绍
     * @param personal
     * @param userId
     * @return
     */
    int updateUserPersonal(String personal,String phone);

    /**
     * 查询用户个人介绍
     * @param userId
     * @return
     */
    String selectUserPersonal(String phone);
}
