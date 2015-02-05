package com.MXBean;

import java.beans.ConstructorProperties;

public class School {
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	int age;
	@ConstructorProperties({"name","age"})
	public School(String name, int age) {
	this.name=name;
	this.age=age;
	}
}
