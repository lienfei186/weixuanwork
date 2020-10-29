package com.cn.weixuan.service;

import com.cn.weixuan.pojo.WorkExperience;

import java.util.List;

public interface IWorkExperienceService {
    int insertUserWork(WorkExperience entity,String phone);

    List<WorkExperience> selectWork(String phone);

    int updateWork(WorkExperience entity);

    int deleteWork(Integer workId);
}
