package com.beanassemble.javatask.week051.annotationDemo;

import com.beanassemble.javatask.week051.model.School;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/17 下午6:33
 * @Version 1.0
 **/
public class AnnotationDemo {

	// 通过注解扫描获取bean
	public static void main(String args[]){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");

		School school = applicationContext.getBean(School.class);

		System.out.println(school.toString());
	}
}
