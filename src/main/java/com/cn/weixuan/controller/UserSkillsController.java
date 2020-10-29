package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.GoodAT;
import com.cn.weixuan.service.IGoodATService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/skills")
public class UserSkillsController {
    @Autowired
    private IGoodATService iGoodATService;
    @Autowired
    private Response response;

    /**
     * 擅长技能添加
     * @param
     * @param phone
     * @return
     */
    @RequestMapping(value = "/saveSkills",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveSkills(GoodAT entity,String phone){
         int rows = iGoodATService.insertGoodAT(entity,phone);
         if (rows > 0){
             return response.success("技能添加成功",null);
         }
         return response.failure("技能添加失败");
    }

    /**
     * 擅长技能查询
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findSkills",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findSkills(String phone){
       List<GoodAT> goodATList = iGoodATService.selectUserSkills(phone);
       if (goodATList != null || goodATList.size()>0){
           return response.success("技能查询成功",goodATList);
       }
       return response.failure("技能查询失败");
    }
}
