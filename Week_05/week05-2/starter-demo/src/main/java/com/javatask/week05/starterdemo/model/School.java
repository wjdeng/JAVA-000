package com.javatask.week05.starterdemo.model;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/18 下午2:51
 * @Version 1.0
 **/
public class School {

	private String schoolName;
	private String schoolAddress;

	public School(String schoolName, String schoolAddress) {
		this.schoolName = schoolName;
		this.schoolAddress = schoolAddress;
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
		return "School{" +
				"schoolName='" + schoolName + '\'' +
				", schoolAddress='" + schoolAddress + '\'' +
				'}';
	}
}
