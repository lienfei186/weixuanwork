package com.cn.weixuan.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class CommonConfiguration {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        /**
         * 图片上传配置
         */
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置单个图片最大
        factory.setMaxFileSize("10240KB");
        return factory.createMultipartConfig();
    }
}
