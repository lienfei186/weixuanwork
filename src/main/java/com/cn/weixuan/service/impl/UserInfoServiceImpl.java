package com.cn.weixuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.weixuan.base.module.CacheUser;
import com.cn.weixuan.config.LoginType;
import com.cn.weixuan.config.MyUsernamePasswordToken;
import com.cn.weixuan.dao.UserInfoMapper;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.UserInfo;
import com.cn.weixuan.service.IUserService;
import com.cn.weixuan.service.UserInfoService;
import com.cn.weixuan.web.exception.LoginException;

import cn.hutool.log.Log;
import lombok.extern.slf4j.Slf4j;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class UserInfoServiceImpl implements UserInfoService{

	@Resource
	public UserInfoMapper userInfoMapper;
	
	@Override
	public int insertUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(userInfo.getTelephone())) {
			log.info("未传参数！"+userInfo.getTelephone()+"为空");
            return -1;
		}else if (userInfoMapper.insertUserInfo(userInfo)<0) {
			log.info("服务器错误，未传成功！");
            return -1;
		}else 
			log.info("添加成功！");
		User user = userInfoMapper.selectUserInfo(userInfo.getTelephone());
		return userInfoMapper.insertUserInfo(userInfo);
	}

	@Override
	public User selectUserInfo(String phone) {
		// TODO Auto-generated method stub
		log.info("查询用户信息成功！");
		return userInfoMapper.selectUserInfo(phone);
	}

	@Override
	public UserInfo selectUserInfoIdcard(Integer id) {
		// TODO Auto-generated method stub
		log.info("查询身份上传者信息成功！");
		return userInfoMapper.selectUserInfoIdcard(id);
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		log.info("修改身份上传者信息成功！");
		return userInfoMapper.updateUserInfo(userInfo);
	}
}
