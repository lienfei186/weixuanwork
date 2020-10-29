package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.UserEstimate;
import com.cn.weixuan.service.IUserEstimateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/estimate")
public class UserEstimateController {
    @Autowired
    private IUserEstimateService iUserEstimateService;
    @Autowired
    private Response response;

    /**
     * 评论添加操作
     * @param entity
     * @param phone
     * @return
     */
    @RequestMapping(value = "/saveEstimate",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveEstimate(UserEstimate entity, String phone){
        int rows = iUserEstimateService.insertEstimate(entity,phone);
        if (rows > 0){
            return response.success("评论添加成功");
        }
        return response.failure("评论添加失败");
    }

    /**
     * 查询用户评论详情
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findEstimate",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findEstimate(String phone){
       List<UserEstimate> userEstimates = iUserEstimateService.findObjects(phone);
       if (userEstimates!=null||userEstimates.size()>0){
           return response.success("查询评论成功",userEstimates);
       }
       return response.failure("查询评论失败",null);
    }

    /**
     * 获取用户评论总数
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getEstimatesCount",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getEstimatesCount(String phone){
        Integer rows = iUserEstimateService.getEstimatesCount(phone);
        if (rows > 0){
            return response.success("查询总数量成功",rows);
        }
        return response.failure("查询总数量成功失败");
    }

    /**
     * 删除评论信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteEstimates",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteEstimates(Integer id){
        int rows = iUserEstimateService.deleteEstimates(id);
        if (rows > 0){
            return response.success("删除评论成功");
        }
        return response.failure("删除评论失败");
    }

    /**
     * 修改用户评论
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateEstimates",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateEstimates(UserEstimate entity){
        int rows = iUserEstimateService.updateEstimates(entity);
        if (rows > 0){
            return response.success("修改评论成功");
        }
        return response.failure("修改评论失败");
    }
}
