package com.cn.weixuan.config;


import com.cn.weixuan.web.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    //自定义过滤器 测试 使用
    @Bean
    public FilterRegistrationBean<MyFilter> testFilterRegistration() {
        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<MyFilter>();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
