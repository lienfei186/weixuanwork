package com.cn.weixuan.service;

import org.apache.ibatis.annotations.Param;

import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.UserInfo;

public interface UserInfoService {

	/***
	 * 
	 * @author by
	 * UserInfo
	 */
	public int insertUserInfo(UserInfo userInfo);
	
	/**
	 * 
	 * @author by
	 * telphone
	 */
	public User selectUserInfo(String phone);
	
	/***
	 * 查找修改信息单条信息
	 * @author by
	 * CreditInfo
	 */
	public UserInfo selectUserInfoIdcard(Integer id);
	
	/***
	 * 修改个人信息
	 * @author by
	 * CreditInfo
	 */
	public int updateUserInfo(UserInfo userInfo);
}
