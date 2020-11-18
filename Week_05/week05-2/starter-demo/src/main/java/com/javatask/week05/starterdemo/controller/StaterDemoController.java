package com.javatask.week05.starterdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午2:29
 * @Version 1.0
 **/

@Controller
@RequestMapping(value = "/starter")
public class StaterDemoController {


	@RequestMapping("/demo")
	@ResponseBody
	public String demo(){
		return "hello starter";
	}
}
