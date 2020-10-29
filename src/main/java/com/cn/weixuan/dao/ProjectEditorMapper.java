package com.cn.weixuan.dao;

import com.cn.weixuan.pojo.ProjectEditor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectEditorMapper {
    /**
     * 添加项目
     * @param entity
     * @return
     */
    int insertProjectEditor(ProjectEditor entity);

    /**
     * 查询项目
     * @param userId
     * @return
     */
    List<ProjectEditor> selectProject(Integer userId);

    /**
     * 根据项目id删除项目
     * @param projectId
     * @return
     */
   int deleteProject(Integer projectId);

    /**
     * 根据项目id删除 图片信息
     * @param projectId
     * @return
     */
   String findPicture(Integer projectId);

    /**
     * 根据项目id修改项目
     * @param projectId
     * @return
     */
   int updateProject(ProjectEditor entity);

}
