package com.cn.weixuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.weixuan.pojo.Project;
import com.cn.weixuan.pojo.UserRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
public interface ProjectMapper extends BaseMapper<Project> {
    /**
     * 查询项目名称
     * @param projectId
     * @return
     */
    Project getProjectName(Integer projectId);
}
