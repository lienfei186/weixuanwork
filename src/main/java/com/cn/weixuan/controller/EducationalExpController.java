package com.cn.weixuan.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.EducationalExp;
import com.cn.weixuan.service.IEducationalExpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/educational")
public class EducationalExpController {
    @Autowired
    private IEducationalExpService iEducationalExpService;
    @Autowired
    private Response response;

    /**
     * 教育经历添加
     * @param entity 教育经历
     * @param phone 用户电话 用于获取用户id
     * @return
     */
    @RequestMapping(value = "/saveEducational",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveEducational(EducationalExp entity,String phone){
        int rows = iEducationalExpService.insertUserEducational(entity,phone);
        if (rows > 0 ){
            return response.success("教育经历添加成功");
        }
        return response.failure("教育经历添加失败");
    }
    /**
     * 根据用户电话查询用户教育经历
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findEducational",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findEducational(String phone){
        List<EducationalExp> list = iEducationalExpService.selectEducational(phone);
        if (list.size()>0 || list != null){
            return response.success("查询工作经历成功",list);
        }
        return response.failure("查询工作经历失败");
    }
    /**
     * 修改用户教育经历
     * @return
     */
    @RequestMapping(value = "/updateEducational",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateEducational(EducationalExp educationalExp){
        int rows = iEducationalExpService.updateEducational(educationalExp);
        if (rows > 0){
            return response.success("修改工作经历成功");
        }
        return response.failure("修改工作经历失败");
    }
    /**
     * 删除用户教育经历
     * @param educationId
     * @return
     */
    @RequestMapping(value = "/deleteEducational",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteEducational(Integer educationId){
        int rows = iEducationalExpService.deleteEducational(educationId);
        if (rows > 0){
            return response.success("删除教育经历成功");
        }
        return response.failure("删除教育经历失败");
    }
}
