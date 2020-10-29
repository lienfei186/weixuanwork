package com.cn.weixuan.dao;//package com.cn.weixuan.dao;

import com.cn.weixuan.pojo.Adress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdressMapper {

    //地址添加
    public int insertAdress(Adress adress);
}
