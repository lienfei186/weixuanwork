package com.cn.weixuan.service;

import com.cn.weixuan.pojo.GoodAT;

import java.util.List;

public interface IGoodATService {
    /*添加技能信息*/
    int insertGoodAT(GoodAT entity, String phone);
    /*查询技能信息*/
    List<GoodAT> selectUserSkills(String phone);
}
