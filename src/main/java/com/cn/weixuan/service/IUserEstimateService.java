package com.cn.weixuan.service;

import com.cn.weixuan.pojo.UserEstimate;

import java.util.List;

public interface IUserEstimateService {

    int insertEstimate(UserEstimate entity,String phone);

    List<UserEstimate> findObjects(String phone);

    Integer getEstimatesCount(String phone);

    int deleteEstimates(Integer id);

    int updateEstimates(UserEstimate entity);
}
