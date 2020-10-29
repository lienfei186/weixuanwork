package com.cn.weixuan.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProjectMapper {
    int getUserId(Integer projectId);
}
