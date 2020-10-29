package com.cn.weixuan.service;

import com.cn.weixuan.pojo.EducationalExp;

import java.util.List;

public interface IEducationalExpService {
    int insertUserEducational(EducationalExp entity,String phone);

    List<EducationalExp> selectEducational(String phone);

    int updateEducational(EducationalExp educationalExp);

    int deleteEducational(Integer educationId);
}
