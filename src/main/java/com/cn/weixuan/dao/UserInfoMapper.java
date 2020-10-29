package com.cn.weixuan.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cn.weixuan.pojo.CreditInfo;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.UserInfo;

@Mapper
public interface UserInfoMapper {

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
	public User selectUserInfo(@Param("phone") String phone);
	
	/***
	 * 查找修改信息单条信息
	 * @author by
	 * CreditInfo
	 */
	public UserInfo selectUserInfoIdcard(@Param("id") Integer id);
	
	/***
	 * 修改个人信息
	 * @author by
	 * CreditInfo
	 */
	public int updateUserInfo(UserInfo userInfo);
}
