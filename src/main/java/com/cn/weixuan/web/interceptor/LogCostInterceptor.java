package com.cn.weixuan.web.interceptor;

import com.cn.weixuan.base.module.CacheUser;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.util.IPUtil;
import com.cn.weixuan.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogCostInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("LogCostInterceptor preHandle");
        User user = UserUtils.getUser();
//        if(user!=null) {
////            log.info("【拦截器日志】-【用户：{}】-【IP：{}】-【访问地址：{}】 ", user.getName(), IPUtil.getIpAddr(httpServletRequest), httpServletRequest.getServletPath());
////        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("LogCostInterceptor postHandle");
        User user = UserUtils.getUser();
        if(user!=null) {
            log.info("【拦截器日志】-【用户：{}】-【IP：{}】-【访问地址：{}】 ", user.getName(), IPUtil.getIpAddr(httpServletRequest), httpServletRequest.getServletPath());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("LogCostInterceptor afterCompletion");
        User user = UserUtils.getUser();
    }
}
