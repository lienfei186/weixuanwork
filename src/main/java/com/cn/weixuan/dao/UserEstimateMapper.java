package com.cn.weixuan.dao;

import com.cn.weixuan.pojo.UserEstimate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserEstimateMapper {
    /**
     * 添加用户评论
     * @param entity
     * @return
     */
    int insertEstimate(UserEstimate entity);

    /**
     * 查询此用户对此项目是否有过评价
     * @param userId
     * @param projectId
     * @return
     */
    int selectCountEstimate(Integer userId,Integer projectId);

    /**
     * 根据用户id查询用户的评论
     * @param
     * @return
     */
    List<UserEstimate> findObjects(Integer userId);

    /**
     * 查询评论总数量
     * @param userId
     * @return
     */
    Integer getEstimatesCount(Integer userId);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int deleteEstimates(Integer id);

    /**
     * 修改评论
     * @param entity
     * @return
     */
    int updateEstimates(UserEstimate entity);

    /**
     * 查询用户修改次数
     * @param id
     * @return
     */
    Integer getStatus(Integer id);
}
