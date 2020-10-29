package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.WorkExperience;
import com.cn.weixuan.service.IWorkExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/work")
public class WorkController {
    @Autowired
    private IWorkExperienceService iWorkExperienceService;
    @Autowired
    private Response response;

    /**
     * 工作经历添加
     * @param entity 工作经历
     * @param phone 获取userid 关联
     * @return
     */
    @RequestMapping(value = "/saveWork",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveWork(WorkExperience entity,String phone){
        int rows = iWorkExperienceService.insertUserWork(entity,phone);
        if (rows > 0){
            return response.success("工作经历添加成功",null);
        }
        return response.failure("工作经历添加失败");
    }

    /**
     * 根据用户电话查询用户工作经历
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findWork",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findWork(String phone){
        List<WorkExperience> workList = iWorkExperienceService.selectWork(phone);
        if (workList.size()>0 || workList != null){
            return response.success("查询工作经历成功",workList);
        }
        return response.failure("查询工作经历失败");
    }

    /**
     * 修改用户工作经历
     * @return
     */
    @RequestMapping(value = "/updateWork",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateWork(WorkExperience workExperience){
        int rows = iWorkExperienceService.updateWork(workExperience);
        if (rows > 0){
            return response.success("修改工作经历成功");
        }
        return response.failure("修改工作经历失败");
    }

    /**
     * 删除用户工作经历
     * @param workId
     * @return
     */
    @RequestMapping(value = "/deleteWork",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteWork(Integer workId){
       int rows = iWorkExperienceService.deleteWork(workId);
       if (rows > 0){
           return response.success("删除工作经历成功");
       }
       return response.failure("删除工作经历失败");
    }
}
