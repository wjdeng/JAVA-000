package com.beanassemble.javatask.week051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/17 下午5:55
 * @Version 1.0
 **/

@Controller
@RequestMapping("/app")
public class TestController {

	@RequestMapping("/test")
	@ResponseBody
	public String testDemo(){
		return "hello world!";
	}
}
