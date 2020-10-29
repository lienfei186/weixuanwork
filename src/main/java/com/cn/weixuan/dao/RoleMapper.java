package com.cn.weixuan.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.weixuan.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 添加角色信息
     * @param entity 角色信息
     * @return
     */
    int insertRole(Role entity);
    List<Role> getUserRoles(@Param("userId") Integer userId);
}
