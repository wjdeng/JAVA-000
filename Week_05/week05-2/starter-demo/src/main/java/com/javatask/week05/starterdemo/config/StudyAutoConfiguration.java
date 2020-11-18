package com.javatask.week05.starterdemo.config;

import com.javatask.week05.starterdemo.model.Klass;
import com.javatask.week05.starterdemo.model.School;
import com.javatask.week05.starterdemo.model.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午2:59
 * @Version 1.0
 **/

@Configuration
@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(MyStudyProperties.class)
public class StudyAutoConfiguration {

	@Resource
	private MyStudyProperties myStudyProperties;

	@Bean
	@ConditionalOnMissingBean(Student.class)
	@ConditionalOnProperty(prefix = "study", value = "enabled", havingValue = "true")
	public Student student(){
		Student student = new Student(myStudyProperties.getStudentId(), myStudyProperties.getStudentName());
		return student;
	}

	@Bean
	@ConditionalOnMissingBean(Klass.class)
	@ConditionalOnProperty(prefix = "study", value = "enabled", havingValue = "true")
	public Klass klass(){
		Klass klass = new Klass(myStudyProperties.getKlassId(), myStudyProperties.getKlassName());
		return klass;
	}

	@Bean
	@ConditionalOnMissingBean(School.class)
	@ConditionalOnProperty(prefix = "study", value = "enabled", havingValue = "true")
	public School school(){
		School school = new School(myStudyProperties.getSchoolName(), myStudyProperties.getSchoolAddress());
		return school;
	}
}
