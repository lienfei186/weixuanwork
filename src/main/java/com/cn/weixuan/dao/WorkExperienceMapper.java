package com.cn.weixuan.dao;

import com.cn.weixuan.pojo.WorkExperience;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//工作经历
@Mapper
public interface WorkExperienceMapper {
    /**
     * 工作经历添加
     * @param entity
     * @return
     */
    int insertUserWork(WorkExperience entity);

    /**
     * 工作经历查询
     * @param userId
     * @return
     */
    List<WorkExperience> selectWork(Integer userId);

    /**
     * 修改工作经历信息
     * @param entity
     * @return
     */
    int updateWork(WorkExperience entity);

    /**
     * 基于工作经历id删除工作经历
     * @param workId
     * @return
     */
    int deleteWork(Integer workId);
}
