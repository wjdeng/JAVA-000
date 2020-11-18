package com.javatask.week05.starteruser.controller;

import com.javatask.week05.starterdemo.model.Klass;
import com.javatask.week05.starterdemo.model.School;
import com.javatask.week05.starterdemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午3:33
 * @Version 1.0
 **/

@Controller
@RequestMapping(value = "/starter")
public class StarterUserController {


	@Autowired
	private Student student;

	@Autowired
	private School school;

	@Autowired
	private Klass klass;

	@RequestMapping("/demo")
	@ResponseBody
	public Map<String, Object> starterUserDemo(){

		Map<String, Object> result = new HashMap<>();
		result.put("student",student);
		result.put("school",school);
		result.put("klass",klass);
		return result;
	}

}
