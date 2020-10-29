package com.cn.weixuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.weixuan.dao.ProjectMapper;
import com.cn.weixuan.pojo.Project;
import com.cn.weixuan.service.IProjectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YHY
 * @since 2020-06-28
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
