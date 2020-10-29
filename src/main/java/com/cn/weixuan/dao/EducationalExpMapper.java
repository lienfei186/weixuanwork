package com.cn.weixuan.dao;

import com.cn.weixuan.pojo.EducationalExp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//教育经历
@Mapper
public interface EducationalExpMapper {
    /**
     * 教育经历添加
     * @param entity
     * @return
     */
    int insertUserEducational(EducationalExp entity);

    /**
     * 教育经历查询
     * @param userId
     * @return
     */
    List<EducationalExp> selectEducational(Integer userId);
    //修改教育经历
    int updateEducational(EducationalExp educationalExp);
    //删除教育经历
    int deleteEducational(Integer educationId);
}
