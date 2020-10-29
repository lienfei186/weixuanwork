package com.cn.weixuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 * 
 * @author by2020/10/10
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.cn.weixuan.dao")
public class SpringWeixuanApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringWeixuanApplication.class, args);
	}
}
