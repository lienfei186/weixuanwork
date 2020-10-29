package com.cn.weixuan.service;

import com.cn.weixuan.pojo.ProjectEditor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProjectEditorService {

    int insertProjectEditor(List<MultipartFile> files, ProjectEditor entity, String phone);

    List<ProjectEditor> selectProject(String phone);

    int deleteProject(Integer projectId);

    int updateProject(ProjectEditor entity,List<MultipartFile> files);
}
