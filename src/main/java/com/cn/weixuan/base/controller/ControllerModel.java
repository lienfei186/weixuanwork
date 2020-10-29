package com.cn.weixuan.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/ws")
public class ControllerModel {

	@RequestMapping("/page")
	public String page() {
		return "/index2.html";
	}
}
