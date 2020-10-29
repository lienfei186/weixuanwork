package com.cn.weixuan.service;

import org.apache.ibatis.annotations.Param;

import com.cn.weixuan.pojo.CreditInfo;
import com.cn.weixuan.pojo.User;

public interface CreditInfoService {

	/***
	 * 
	 * @author by
	 * UserInfo
	 */
	public int insertCreditInfo(CreditInfo creditInfo);
	
	/***
	 * 查找修改信息单条信息
	 * @author by
	 * CreditInfo
	 */
	public CreditInfo selectCreditInfoId(Integer id);
	
	/***
	 * 修改执照信息
	 * @author by
	 * CreditInfo
	 */
	public int updateCreditInfo(CreditInfo creditInfo);
}
