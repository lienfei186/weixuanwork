package com.cn.weixuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.weixuan.base.module.CacheUser;
import com.cn.weixuan.base.module.SystemConstant;
import com.cn.weixuan.config.LoginType;
import com.cn.weixuan.config.MyUsernamePasswordToken;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.service.IUserService;
import com.cn.weixuan.web.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    UserMapper userMapper;



    @Override
    public User findByUsername(String username) {
        User user = this.baseMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUserName, username)
                        .or()
                        .eq(User::getPhone, username)
        );
        return user;
    }

    @Override
    public User findByPhone(String phone) {
        User user = this.baseMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getPhone, phone)
        );
        return user;
    }

    @Override
    public CacheUser login(String userName, String password) {
        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名和密码封装到UsernamePasswordToken
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(userName, password, LoginType.PASSWD);

        CacheUser cacheUser;

        // 4、认证
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder()
                    .token(currentUser.getSession().getId().toString())
                    .build();
            BeanUtils.copyProperties(user, cacheUser);
            //登录成功
            //获取用户角色

        } catch (UnknownAccountException e) {
            log.error("账户不存在异常：", e);
            throw new LoginException("账号不存在!", e);
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);
            throw new LoginException("密码不正确!", e);
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
            throw new LoginException("用户验证失败!", e);
        }
        return cacheUser;
    }

    @Override
    public int regist(User user) {
        User findByUserName = this.findByUsername(user.getUserName());
        User findByPhone = this.findByUsername(user.getPhone());
        if (findByUserName != null || findByPhone != null) {
            log.info("用户已经存在");
            return -1;
        }
        //密码 begin
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        //ByteSource salt = ByteSource.Util.bytes(user.getPassword());
        String password = new SimpleHash("MD5", user.getPassword(), salt, 2).toHex();
        user.setPassword(password);
        user.setSalt(salt);
        //密码 end
        //其它用户信息
        user.setCreateTime(new Date());
        user.setState(0);

        return userMapper.regist(user);
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public List<User> listUsers() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }

    /**
     * 根据用户电话获取用户个人信息
     * @param phone
     * @return
     */
    @Override
    public User findByUserList(String phone) {
       User user = userMapper.selectUserId(phone);
       User userList =  userMapper.findByUserList(user.getUserId());
       userList.setProfile_path(userMapper.selectprofilePath(user.getUserId()));
        return userList;
    }

    /**
     * 用户头像添加
     * @param profile
     * @return
     */
    @Override
    public int updUserProfile(MultipartFile profile) {
        //根据操作系统不同设置头像保存路径
        String OSName = System.getProperty("os.name");
        String profilesPath = OSName.toLowerCase().startsWith("win") ? SystemConstant.WINDOWS_PROFILES_PATH : SystemConstant.LINUX_PROFILES_PATH;
        if (!profile.isEmpty()){
            //当前用户
            User  currentUser = (User) SecurityUtils.getSubject().getPrincipal();
            String profilePathAndNameDB = userMapper.selectprofilePath(currentUser.getUserId());
            // 默认以原来的头像名称为新头像的名称，这样可以直接替换掉文件夹中对应的旧头像
            String newProfileName = profilePathAndNameDB;
            // 若头像名称不存在
            if (profilePathAndNameDB == null || "".equals(profilePathAndNameDB)) {
                newProfileName = profilesPath+ System.currentTimeMillis()+ profile.getOriginalFilename();
                // 路径存库
                userMapper.updateUserProfilePath(newProfileName,currentUser.getUserId());
            }
            //保存磁盘
            BufferedOutputStream out = null;
            try {
                File folder = new File(profilesPath);
                if (!folder.exists()){
                    folder.mkdirs();
                }
                out = new BufferedOutputStream(new FileOutputStream(newProfileName));
                out.write(profile.getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return 1;
        }else {
            return 0;
        }

    }

    /**
     * 修改用户个人介绍
     * @param personal
     * @param phone
     * @return
     */
    @Override
    public int updateUserPersonal(String personal, String phone) {
        User user =userMapper.selectUserId(phone);
        int rows = userMapper.updateUserPersonal(personal,user.getUserId());
        return rows;
    }

    /**
     * 查询用户个人介绍
     * @param phone
     * @return
     */
    @Override
    public String selectUserPersonal(String phone) {
        User user =userMapper.selectUserId(phone);
        String personal = userMapper.selectUserPersonal(user.getUserId());
        return personal;
    }
}
