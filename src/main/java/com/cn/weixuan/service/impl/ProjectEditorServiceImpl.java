package com.cn.weixuan.service.impl;

import com.cn.weixuan.base.module.SystemConstant;
import com.cn.weixuan.config.UploadFileUtil;
import com.cn.weixuan.dao.EducationalExpMapper;
import com.cn.weixuan.dao.ProjectEditorMapper;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.ProjectEditor;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.service.IPermissionService;
import com.cn.weixuan.service.IProjectEditorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProjectEditorServiceImpl implements IProjectEditorService {
    @Autowired
    private ProjectEditorMapper projectEditorMapper;
    @Autowired
    private UserMapper userMapper;
    //项目添加
    @Override
    public int insertProjectEditor(List<MultipartFile> files, ProjectEditor entity, String phone) {
        //根据操作系统不同设置头像保存路径
        String OSName = System.getProperty("os.name");
        String profilesPath = OSName.toLowerCase().startsWith("win") ? SystemConstant.WINDOWS_PROFILES_PATH : SystemConstant.LINUX_PROFILES_PATH;
        //文件保存目录如果有则不创建
        File dir = new File(profilesPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        List<String> paths = null;
        try {
            paths = UploadFileUtil.uploadFiles(files,profilesPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(paths.get(0).equals("file_empty") || paths.get(0).equals("wrong_file_extension")) {

            return 0;
        }
        User user = userMapper.selectUserId(phone);
        entity.setUserId(user.getUserId());
        entity.setPicture(paths.toString());
        int rows = projectEditorMapper.insertProjectEditor(entity);
        return rows;
    }
    //查询项目
    @Override
    public List<ProjectEditor> selectProject(String phone) {
        User user = userMapper.selectUserId(phone);
        List<ProjectEditor> proList = projectEditorMapper.selectProject(user.getUserId());
        return proList;
    }
    //删除项目
    @Override
    public int deleteProject(Integer projectId) {
        //先删除图片
        String paths = projectEditorMapper.findPicture(projectId);
        if (paths!=null || paths.length()>0){
           Boolean del = UploadFileUtil.deleteFile(paths);
           if (del){
               int rows = projectEditorMapper.deleteProject(projectId);
               return rows;
           }else {
               return 0;
           }
        }else {
            int rows = projectEditorMapper.deleteProject(projectId);
            return rows;
        }
    }

    @Override
    public int updateProject(ProjectEditor entity,List<MultipartFile> files) {
        //先删除之前的图片
        String paths = projectEditorMapper.findPicture(entity.getProjectId());
        if (paths != null || paths.length() > 0) {
            Boolean del = UploadFileUtil.deleteFile(paths);
            if (del) {
                //根据操作系统不同设置头像保存路径
                String OSName = System.getProperty("os.name");
                String profilesPath = OSName.toLowerCase().startsWith("win") ? SystemConstant.WINDOWS_PROFILES_PATH : SystemConstant.LINUX_PROFILES_PATH;
                //文件保存目录如果有则不创建
                File dir = new File(profilesPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                List<String> pathList = null;
                try {
                    pathList = UploadFileUtil.uploadFiles(files, profilesPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (pathList.get(0).equals("file_empty") || pathList.get(0).equals("wrong_file_extension")) {

                    return 0;
                }
                entity.setPicture(pathList.toString());
                int rows = projectEditorMapper.updateProject(entity);
                return rows;
            } else {
               return 0;
            }

        }else {
            //根据操作系统不同设置头像保存路径
            String OSName = System.getProperty("os.name");
            String profilesPath = OSName.toLowerCase().startsWith("win") ? SystemConstant.WINDOWS_PROFILES_PATH : SystemConstant.LINUX_PROFILES_PATH;
            //文件保存目录如果有则不创建
            File dir = new File(profilesPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            List<String> pathList = null;
            try {
                pathList = UploadFileUtil.uploadFiles(files, profilesPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (pathList.get(0).equals("file_empty") || pathList.get(0).equals("wrong_file_extension")) {

                return 0;
            }
            entity.setPicture(pathList.toString());
            int rows = projectEditorMapper.updateProject(entity);
            return rows;
        }

    }
}
