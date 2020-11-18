package com.javatask.week05.starterdemo.model;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午2:32
 * @Version 1.0
 **/
public class Student {

	private Integer studentId;
	private String studentName;

	public Student(Integer studentId, String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	}

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

	@Override
	public String toString() {
		return "Student{" +
				"studentId=" + studentId +
				", studentName='" + studentName + '\'' +
				'}';
	}
}
