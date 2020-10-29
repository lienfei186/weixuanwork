package com.cn.weixuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.weixuan.base.module.CacheUser;
import com.cn.weixuan.config.LoginType;
import com.cn.weixuan.config.MyUsernamePasswordToken;
import com.cn.weixuan.dao.CreditInfoMapper;
import com.cn.weixuan.dao.UserInfoMapper;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.CreditInfo;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.UserInfo;
import com.cn.weixuan.service.CreditInfoService;
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
public class CreditInfoServiceImpl implements CreditInfoService {

	@SuppressWarnings("unused")
	@Autowired
	private CreditInfoMapper creditInfoMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public int insertCreditInfo(CreditInfo creditInfo) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(creditInfo.getTelephone())) {
			log.info("未传参数！" + creditInfo.getTelephone() + "为空");
			return -1;
		} else if (creditInfoMapper.insertCreditInfo(creditInfo) < 0) {
			log.info("服务器错误，未传成功！");
			return -1;
		} else
			log.info("添加成功！");
		//关联查询信息然后insert添加
		User user = userInfoMapper.selectUserInfo(creditInfo.getTelephone());
		creditInfo.setUserId(user.getUserId().toString());
		creditInfo.setUserType(user.getRole());
		return creditInfoMapper.insertCreditInfo(creditInfo);
	}

	@Override
	public CreditInfo selectCreditInfoId(Integer id) {
		// TODO Auto-generated method stub
		log.info("查询修改执照信息成功！");
		return creditInfoMapper.selectCreditInfoId(id);
	}

	@Override
	public int updateCreditInfo(CreditInfo creditInfo) {
		// TODO Auto-generated method stub
		log.info("修改执照信息成功！");
		return creditInfoMapper.updateCreditInfo(creditInfo);
	}

}
