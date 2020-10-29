package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.ProjectEditor;
import com.cn.weixuan.service.IProjectEditorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/projectEditor")
public class ProjectEditorController {
    @Autowired
    private IProjectEditorService iProjectEditorService;
    @Autowired
    private Response response;

    /**
     * 添加项目信息
     * @param files 图片信息
     * @param entity 项目信息
     * @param phone 用户电话
     * @return
     */
    @RequestMapping(value = "/saveProjectEditor",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveProject(List<MultipartFile> files, ProjectEditor entity, String phone){
        int rows = iProjectEditorService.insertProjectEditor(files,entity,phone);
        if (rows > 0){
            return response.success("项目添加成功");
        }
        return response.failure("项目添加失败");
    }

    /**
     * 查询项目信息
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findProjectEditor",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findProject(String phone){
        List<ProjectEditor> proList =  iProjectEditorService.selectProject(phone);
        if (proList!=null||proList.size() > 0){
            return response.success("项目查询成功",proList);
        }
        return response.failure("项目查询失败");
    }

    /**
     * 根据项目id删除项目
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/deleteProjectEditor",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteProject(Integer projectId){
        int rows = iProjectEditorService.deleteProject(projectId);
        if (rows > 0){
            return response.success("项目删除成功");
        }
        return response.failure("项目删除失败");
    }

    /**
     * 修改项目信息
     * @param entity
     * @param files
     * @return
     */
    @RequestMapping(value = "/updateProjectEditor",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateProject(ProjectEditor entity,List<MultipartFile> files){
       int rows =  iProjectEditorService.updateProject(entity,files);
        if (rows > 0){
            return response.success("项目修改成功");
        }
        return response.failure("项目修改失败");
    }
}
