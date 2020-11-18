package com.beanassemble.javatask.week051.xmlDemo;

import com.beanassemble.javatask.week051.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/17 下午6:25
 * @Version 1.0
 **/
public class XMLDemo {

	// 通过XML配置获取bean
	public static void main(String[] args){

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");
		Student student = (Student) applicationContext.getBean("wayne");
		System.out.println(student.toString());
	}
}
