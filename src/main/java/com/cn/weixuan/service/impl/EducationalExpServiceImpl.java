package com.cn.weixuan.service.impl;

import com.cn.weixuan.dao.EducationalExpMapper;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.EducationalExp;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.service.IEducationalExpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EducationalExpServiceImpl implements IEducationalExpService {
    @Autowired
    private EducationalExpMapper educationalExpMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 教育经历添加
     * @param entity
     * @param phone
     * @return
     */
    @Override
    public int insertUserEducational(EducationalExp entity,String phone) {
        //获取用户id
        User user = userMapper.selectUserId(phone);
        entity.setUserId(user.getUserId());
        int rows = educationalExpMapper.insertUserEducational(entity);
        return rows;
    }
    //查询教育经历
    @Override
    public List<EducationalExp> selectEducational(String phone) {
        User user =  userMapper.selectUserId(phone);
        List<EducationalExp> list = educationalExpMapper.selectEducational(user.getUserId());
        return list;
    }
    //修改教育经历
    @Override
    public int updateEducational(EducationalExp educationalExp) {
        int rows = educationalExpMapper.updateEducational(educationalExp);
        return rows;
    }
    //删除用户教育经历
    @Override
    public int deleteEducational(Integer educationId) {
        int rows = educationalExpMapper.deleteEducational(educationId);
        return rows;
    }
}
