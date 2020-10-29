package com.cn.weixuan.service.impl;

import com.cn.weixuan.dao.ProjectMapper;
import com.cn.weixuan.dao.UserEstimateMapper;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.dao.UserProjectMapper;
import com.cn.weixuan.pojo.Project;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.UserEstimate;
import com.cn.weixuan.service.IUserEstimateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserEstimateServiceImpl implements IUserEstimateService {
    @Autowired
    private UserEstimateMapper userEstimateMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserProjectMapper userProjectMapper;
    /**
     * 添加用户评论
     * @param entity
     * @param phone
     * @return
     */
    @Override
    public int insertEstimate(UserEstimate entity, String phone) {

        User user = userMapper.selectUserId(phone);
        entity.setUserId(user.getUserId());
        //查询评论是否存在
        int count = userEstimateMapper.selectCountEstimate(user.getUserId(),entity.getProjectId());
        if (count > 0)
            throw  new IllegalArgumentException("评论已存在");
        //判断用户是开发者或需求者
        if (user.getRole().isEmpty()) {
            //基于项目id获取项目名称
            Project project = projectMapper.getProjectName(entity.getProjectId());
            //基于项目id获取开发者id 再获取开发者名称
            Integer userId = userProjectMapper.getUserId(entity.getProjectId());
            String userName = userMapper.getUserName(userId);
            entity.setProjectName(project.getName());
            entity.setUserName(userName);
            int rows = userEstimateMapper.insertEstimate(entity);
            return rows;
        }else {
            //基于项目id获取项目名称
            Project project = projectMapper.getProjectName(entity.getProjectId());
            //基于项目id获取 需求方id 再获取需求方名称
            String userName = userMapper.getUserName(project.getUserId());
            entity.setUserName(userName);
            entity.setProjectName(project.getName());
            int rows = userEstimateMapper.insertEstimate(entity);
            return rows;
        }
    }
    //查询用户评论
    @Override
    public List<UserEstimate> findObjects(String phone) {
        User user = userMapper.selectUserId(phone);
        List<UserEstimate> estimateList = userEstimateMapper.findObjects(user.getUserId());

        return estimateList;
    }
    //获取评论数量
    @Override
    public Integer getEstimatesCount(String phone) {
        User user = userMapper.selectUserId(phone);
        Integer rows = userEstimateMapper.getEstimatesCount(user.getUserId());
        return rows;
    }
    //删除评论
    @Override
    public int deleteEstimates(Integer id) {
        int rows = userEstimateMapper.deleteEstimates(id);
        return rows;
    }
    //修改评论
    @Override
    public int updateEstimates(UserEstimate entity ) {
        Integer status = userEstimateMapper.getStatus(entity.getId());
        if (status>0)
            throw new IllegalArgumentException("修改次数达到上限");
        int rows = userEstimateMapper.updateEstimates(entity);
        return rows;
    }
}
