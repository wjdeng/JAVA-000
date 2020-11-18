package com.javatask.week05.starterdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午2:55
 * @Version 1.0
 **/

@ConfigurationProperties(prefix = "study")
public class MyStudyProperties {

	private Integer studentId;
	private String studentName;
	private Integer klassId;
	private String klassName;
	private String schoolName;
	private String schoolAddress;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getKlassId() {
		return klassId;
	}

	public void setKlassId(Integer klassId) {
		this.klassId = klassId;
	}

	public String getKlassName() {
		return klassName;
	}

	public void setKlassName(String klassName) {
		this.klassName = klassName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	@Override
	public String toString() {
		return "MyStudyProperties{" +
				"studentId=" + studentId +
				", studentName='" + studentName + '\'' +
				", klassId=" + klassId +
				", klassName='" + klassName + '\'' +
				", schoolName='" + schoolName + '\'' +
				", schoolAddress='" + schoolAddress + '\'' +
				'}';
	}
}
