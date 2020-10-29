package com.cn.weixuan.service.impl;

import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.dao.WorkExperienceMapper;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.WorkExperience;
import com.cn.weixuan.service.IWorkExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IWorkExperienceServiceImpl implements IWorkExperienceService {
    @Autowired
    private WorkExperienceMapper workExperienceMapper;
    @Autowired
    private UserMapper userMapper;
    //工作经历添加
    @Override
    public int insertUserWork(WorkExperience entity, String phone) {

       User user =  userMapper.selectUserId(phone);
       entity.setUserId(user.getUserId());
       int rows = workExperienceMapper.insertUserWork(entity);
        return rows;
    }
    //查询工作经历
    @Override
    public List<WorkExperience> selectWork(String phone) {
        User user =  userMapper.selectUserId(phone);
        List<WorkExperience> worlList =  workExperienceMapper.selectWork(user.getUserId());
        return worlList;
    }
    //修改工作经历
    @Override
    public int updateWork(WorkExperience entity) {

        int rows = workExperienceMapper.updateWork(entity);
        return rows;
    }
    //删除工作经历
    @Override
    public int deleteWork(Integer workId) {
        int rows = workExperienceMapper.deleteWork(workId);
        return rows;
    }


}
