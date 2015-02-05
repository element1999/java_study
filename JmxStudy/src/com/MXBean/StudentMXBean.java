package com.MXBean;

import javax.management.AttributeChangeNotification;

public interface StudentMXBean {

	public abstract int getAge();

	public abstract void setAge(int age);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract void setSchool(School school);

	public abstract School getSchool();

}