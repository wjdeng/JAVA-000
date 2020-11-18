package com.beanassemble.javatask.week051.model;

import org.springframework.stereotype.Component;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/17 下午6:50
 * @Version 1.0
 **/

@Component
public class School {
	private String name;
	private String addres;

	public School() {
		this.name = "XX大学";
		this.addres = "XX路";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	@Override
	public String toString() {
		return "School{" +
				"name='" + name + '\'' +
				", addres='" + addres + '\'' +
				'}';
	}
}
