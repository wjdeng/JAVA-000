package com.beanassemble.javatask.week051.model;

import java.io.Serializable;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/17 下午6:13
 * @Version 1.0
 **/
public class Student implements Serializable{



	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void init(){
		System.out.println("hello...........");
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
