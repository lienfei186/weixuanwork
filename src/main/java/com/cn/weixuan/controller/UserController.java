package com.cn.weixuan.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.service.IUserService;
import com.cn.weixuan.sms.SMSUtils;
import com.cn.weixuan.util.IPUtil;
import com.cn.weixuan.web.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YHY
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private Response response;
    @Autowired
    private RedisService redisService;

    //发送间隔
    private String registSendTime = "regist_session_time_";
    //验证码
    private String registCode = "regist_session_code_";
    //有效时间 分钟
    private String registCodeEff = "5";
    //发送间隔
    private String repasswdSendTime = "repasswd_session_time_";
    //验证码
    private String repasswdCode = "repasswd_session_code_";
    //有效时间 分钟
    private String repasswdCodeEff = "5";
    //注册发送短信模板
    @Value("${sms.template.regist}")
    private String registTemplate;
    //找回密码发送短信模板
    @Value("${sms.template.repasswd}")
    private String repasswdTemplate;

    /**
     * 注册发送短信
     */
    @ResponseBody
    @RequestMapping(value = "/sendRegistsms", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sendRegistsms(String phone, HttpServletRequest request) {
        log.info("注册发送验证码,手机号：{}", phone);

        if (StringUtils.isBlank(phone)) {
            return response.failure("手机号码为空！");
        }
        //获取上次发送时间 1 分钟发送一次
        Object sendTime = this.redisService.get(registSendTime + phone);
        if (sendTime != null) {
            log.info("请不要重复发送 60 秒发送一次");
            return response.failure("请不要重复发送！");
        }
        String code = SMSUtils.genCode(4);
        String content = SMSUtils.genMsgContent(this.registTemplate, code, registCodeEff);
        //向缓存添加 验证码和时间
        this.redisService.set(registSendTime + phone, "send time 60s", 60L);
        //设置短信验证码有效时间5分钟
        this.redisService.set(registCode + phone, code, 60 * 5L);

        boolean flag = SMSUtils.setSingleSms(phone, content);
        if (flag) {
            return response.success("发送成功！");
        } else {
            return response.failure("发送失败，请稍后在试！");
        }
    }

    /**
     * 用户添加;
     *
     * @return
     */
    @RequestMapping(value = "/regist", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response regist(User user, String code, HttpServletRequest request) {
        if (StringUtils.isBlank(code)) {
            log.info("验证码为空.....");
            return response.failure("验证码为空！");
        }
        String phone = user.getPhone();
        //校验短信验证码
        Object sendCode = this.redisService.get(registCode + phone);
        if (sendCode == null) {
            log.info("验证码过期.....");
            return response.failure("验证码过期");
        }
        if (!StringUtils.equals(code, sendCode.toString())) {
            log.info("验证码不正确.....");
            //删除验证码
            this.redisService.remove(registCode + phone);
            return response.failure("验证码不正确");
        }
        //用户注册信息
        //TODO 验证用户信息 registUser
        user.setRegistIP(IPUtil.getIpAddr(request));
        int r = this.iUserService.regist(user);
        //删除验证码
        this.redisService.remove(registCode + phone);
        if (r > 0) {
            return response.success("注册成功！", null);
        }
        return response.failure("注册失败！", null);
    }

    /**
     * 发送重置密码短信;
     *
     * @return
     */
    @RequestMapping(value = "/sendRepasswdsms", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response sendRepasswdsms(String phone, HttpServletRequest request) {
        log.info("重置密码发送验证码,手机号：{}", phone);

        if (StringUtils.isBlank(phone)) {
            return response.failure("手机号码为空！");
        }
//        User user=this.iUserService.findByPhone(phone);
//        if(user==null){
//            return response.failure("手机号码不正确！");
//        }
        //获取上次发送时间 1 分钟发送一次
        Object sendTime = this.redisService.get(repasswdSendTime + phone);
        if (sendTime != null) {
            log.info("请不要重复发送 60 秒发送一次");
            return response.failure("请不要重复发送！");
        }
        String code = SMSUtils.genCode(4);
        String content = SMSUtils.genMsgContent(this.repasswdTemplate, code, repasswdCodeEff);
        //向缓存添加 验证码和时间
        this.redisService.set(repasswdSendTime + phone, "send time 60s", 60L);
        //设置短信验证码有效时间5分钟
        this.redisService.set(repasswdCode + phone, code, 60 * 5L);

        boolean flag = SMSUtils.setSingleSms(phone, content);
        if (flag) {
            return response.success("发送成功！");
        } else {
            return response.failure("发送失败，请稍后在试！");
        }
    }

    /**
     * 重置密码;
     *
     * @return
     */
    @RequestMapping(value = "/repasswd", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response repasswd(String phone, String code, String password, String newpassword, HttpServletRequest request) {
        log.info("重置密码，手机号:{}", phone);
        if (StringUtils.isBlank(code)) {
            log.info("验证码为空.....");
            return response.failure("验证码为空！");
        }
        //校验短信验证码
        Object sendCode = this.redisService.get(repasswdCode + phone);
        if (sendCode == null) {
            log.info("验证码过期.....");
            return response.failure("验证码过期");
        }
        if (!StringUtils.equals(code, sendCode.toString())) {
            log.info("验证码不正确.....");
            //删除验证码
            this.redisService.remove(repasswdCode + phone);
            return response.failure("验证码不正确");
        }
        if (StringUtils.equals(password, newpassword)) {
            User user = this.iUserService.findByPhone(phone);
            if (user == null) {
                log.info("修改密码失败用不存在.....");
                return response.failure("修改密码失败用不存在！");
            } else {
                log.info("开始更新新密码.....");
                String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
                String hashpassword = new SimpleHash("MD5", password, salt, 2).toHex();
                user.setPassword(hashpassword);
                user.setSalt(salt);
                boolean r = this.iUserService.update(user, new LambdaQueryWrapper<User>()
                        .eq(User::getUserId, user.getUserId()));
                if (!r) {
                    return response.failure("重置密码失败");
                }
            }
        } else {
            return response.failure("两次密码不一致");
        }
        //删除验证码
        this.redisService.remove(repasswdCode + phone);
        return response.success("密码重置成功！");
    }

    /**
     * 个人中心
     * @param phone 用户电话
     *
     * @return
     */
    @RequestMapping(value = "/userInfo",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response userInfo(String phone){
        User userList = iUserService.findByUserList(phone);
        if (userList!=null){
            return response.success("个人信息查询成功",userList);
        }
        return response.failure("信息获取失败",null);

    }

    /**
     * 头像修改和添加
     * @param profile 提交的头像
     * @return
     */
    @RequestMapping(value = "/profile",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setUserProfile(MultipartFile profile){
        int rows = iUserService.updUserProfile(profile);
        if (rows > 0){
            return response.success("头像设置成功",null);
        }
        return response.failure("头像设置失败");
    }
    /**
     * 用户列表;
     *
     * @return
     */
    @RequestMapping(value = "/listUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response listUser(HttpServletRequest request) {
        List<User> users = this.iUserService.listUsers();
        return response.success("查询成功！", users);
    }

    /**
     * 修改用户个人介绍
     * @param personal
     * @param phone
     * @return
     */
    @RequestMapping(value = "/personal",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveUserPersonal(String personal,String phone){
        int rows = iUserService.updateUserPersonal(personal,phone);
        if (rows > 0){
            return response.success("个人介绍修改成功",null);
        }
        return response.failure("个人介绍修改成功");
    }

    /**
     * 查询个人介绍
     * @param phone
     * @return
     */
    @RequestMapping(value = "/findPersonal",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findPersonal(String phone){
        String personal = iUserService.selectUserPersonal(phone);
        if (!personal.equals(null)){
            return response.success("个人介绍查询成功",personal);
        }
        return response.failure("个人介绍查询失败");
    }
}
