package com.cn.weixuan.service.impl;

import com.cn.weixuan.dao.GoodATMapper;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.GoodAT;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.service.IGoodATService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class GoodATServiceImpl implements IGoodATService {
    @Autowired
    private GoodATMapper goodATMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加技能信息
     * @param goodatNames
     * @param phone
     * @return
     */
    @Override
    public int insertGoodAT(GoodAT entity, String phone) {
        User user = userMapper.selectUserId(phone);
        //先判断用户是否存在，存在修改不存在添加
        int count = goodATMapper.countUserId(user.getUserId());
        if (count > 0){
            entity.setUserId(user.getUserId());
            int rows = goodATMapper.updateUserSkills(entity);
            return rows;
        }else {
            entity.setUserId(user.getUserId());
            int rows = goodATMapper.insertGoodAT(entity);
            return rows;
        }


    }
    //查询用户技能
    @Override
    public List<GoodAT> selectUserSkills(String phone) {
        User user = userMapper.selectUserId(phone);
        List<GoodAT> goodATList = goodATMapper.selectUserSkills(user.getUserId());
        return goodATList;
    }

}
