package com.cn.weixuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.weixuan.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface IRoleService extends IService<Role> {
    int saveRole(Role entity,List<Integer> permissionIds);
    List<Role> getUserRoles(Integer userId);
}
