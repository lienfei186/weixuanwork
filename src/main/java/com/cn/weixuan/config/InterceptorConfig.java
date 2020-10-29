package com.cn.weixuan.config;

import com.cn.weixuan.web.interceptor.LogCostInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //日志拦截器
        registry.addInterceptor(new LogCostInterceptor())
                .addPathPatterns("/login","/user/**");


    }

}
