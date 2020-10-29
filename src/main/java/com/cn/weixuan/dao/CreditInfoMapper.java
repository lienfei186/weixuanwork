package com.cn.weixuan.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cn.weixuan.pojo.CreditInfo;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.UserInfo;

@Mapper
public interface CreditInfoMapper {

	/***
	 * 
	 * @author by
	 * CreditInfo
	 */
	public int insertCreditInfo(CreditInfo creditInfo);
	
	/***
	 * 查找修改信息单条信息
	 * @author by
	 * CreditInfo
	 */
	public CreditInfo selectCreditInfoId(@Param("id") Integer id);
	
	/***
	 * 修改执照信息
	 * @author by
	 * CreditInfo
	 */
	public int updateCreditInfo(CreditInfo creditInfo);
	
}
