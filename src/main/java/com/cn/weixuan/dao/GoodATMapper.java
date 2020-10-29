package com.cn.weixuan.dao;

import com.cn.weixuan.pojo.GoodAT;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodATMapper {
    /**
     * 添加用户的擅长技能
     * @param
     * @param
     * @return
     */
    int insertGoodAT(GoodAT entity);
    /**
     * 基于用户id查询用户技能
     *
     */
    List<GoodAT> selectUserSkills(Integer userId);

    /**
     * 查询此用户是否已存在
     * @param userId
     * @return
     */
    int countUserId(Integer userId);

    /**
     * 修改用户技能
     * @param entity
     * @return
     */
    int updateUserSkills(GoodAT entity);
}
