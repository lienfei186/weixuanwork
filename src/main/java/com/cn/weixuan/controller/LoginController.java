package com.cn.weixuan.controller;

import com.cn.weixuan.base.module.CacheUser;
import com.cn.weixuan.base.module.Response;
import com.cn.weixuan.config.LoginType;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.service.IUserService;
import com.cn.weixuan.sms.SMSUtils;
import com.cn.weixuan.web.RedisService;
import com.cn.weixuan.config.MyUsernamePasswordToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ：YHY
 * @date ：2020/10/12 10:23
 * @description：登录Controller
 */
@Slf4j
@RestController
public class LoginController {

    @Resource
    private IUserService iUserService;

    @Resource
    private Response response;

    @Autowired
    private RedisService redisService;

    //发送间隔
    private String loginSendTime = "login_session_time_";
    //验证码
    private String loginCode = "login_session_code_";
    //有效时间 分钟
    private String loginCodeEff = "5";
    //登录发送短信模版
    @Value("${sms.template.login}")
    private String loginTemplate;

    @ResponseBody
    @RequestMapping(value = "/sendLoginsms", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sendsms(String phone, HttpServletRequest request) {
        log.info("短信登录发送验证码,手机号：{}", phone);

        if (StringUtils.isBlank(phone)) {
            return response.failure("手机号码为空！");
        }
        //获取上次发送时间 1 分钟发送一次
        Object sendTime = this.redisService.get(loginSendTime + phone);
        if (sendTime != null) {
            log.info("请不要重复发送 60 秒发送一次");
            return response.failure("请不要重复发送！");
        }
        String code = SMSUtils.genCode(4);
        String content = SMSUtils.genMsgContent(this.loginTemplate, code, loginCodeEff);
        //向缓存添加 验证码和时间
        this.redisService.set(loginSendTime + phone, "send time 60s", 60L);
        //设置短信验证码有效时间5分钟
        this.redisService.set(loginCode + phone, code, 60 * 5L);

        boolean flag = SMSUtils.setSingleSms(phone, content);
        if (flag) {
            return response.success("发送成功！");
        } else {
            return response.failure("发送失败，请稍后在试！");
        }
    }

    /**
     * create by: YHY
     * description: 登录
     * create time: 2020/6/28 17:11
     *
     * @return 登录结果
     */
    @ResponseBody
    @RequestMapping(value = "/smsLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response smsLogin(String code, String phone, HttpServletRequest request) {
        log.info("短信登录进入登录.....");

        if (StringUtils.isBlank(code)) {
            log.info("验证码为空.....");
            return response.failure("验证码为空！");
        }

        if (StringUtils.isBlank(phone)) {
            log.info("手机号为空为空.....");
            return response.failure("手机号为空为空！");
        }
        //校验短信验证码
        Object sendCode = this.redisService.get(loginCode + phone);
        if (sendCode == null) {
            log.info("验证码过期.....");
            return response.failure("验证码过期");
        }
        if (!StringUtils.equals(code, sendCode.toString())) {
            log.info("验证码不正确.....");
            this.redisService.remove(loginCode + phone);
            return response.failure("验证码不正确");
        }
        //使用手机号 查询用户信息
        User user = this.iUserService.findByPhone(phone);
        if (user == null) {
            log.info("用户不存在.....");
            return response.failure("登录失败");
        }

        MyUsernamePasswordToken token = new MyUsernamePasswordToken(phone, LoginType.SMS);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        // 构建缓存用户信息返回给前端
        user = (User) subject.getPrincipals().getPrimaryPrincipal();
        CacheUser cacheUser = CacheUser.builder().token(subject.getSession().getId().toString()).build();
        BeanUtils.copyProperties(user, cacheUser);
        this.redisService.remove(loginCode + phone);
        // 登录成功返回用户信息
        return response.success("登录成功！", cacheUser);
    }

    /**
     * create by: YHY
     * description: 登录
     * create time: 2020/6/28 17:11
     *
     * @return 登录结果
     */
    @ResponseBody
    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response login(User user) {
        log.info("进入登录.....");
        String userName = user.getUserName();
        String password = user.getPassword();

        if (StringUtils.isBlank(userName)) {
            return response.failure("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return response.failure("密码为空！");
        }

        CacheUser loginUser = iUserService.login(userName, password);
        // 登录成功返回用户信息
        return response.success("登录成功！", loginUser);
    }

    /**
     * create by: YHY
     * description: 登出
     * create time: 2020/6/28 17:37
     */
    @RequestMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response logOut() {
        iUserService.logout();
        return response.success("登出成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * <br/>
     * create by: YHY
     * <br/>
     * create time: 2020/7/3 14:53
     *
     * @return
     */
    @RequestMapping(value = "/un_auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response unAuth() {
        return response.failure(HttpStatus.UNAUTHORIZED, "用户未登录！", null);
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * <br/>
     * create by: YHY
     * <br/>
     * create time: 2020/7/3 14:53
     *
     * @return
     */
    @RequestMapping(value = "/unauthorized", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response unauthorized() {
        return response.failure(HttpStatus.FORBIDDEN, "用户无权限！", null);
    }
}
