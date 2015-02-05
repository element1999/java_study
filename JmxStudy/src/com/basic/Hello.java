package com.basic;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class Hello implements HelloMBean{

	int age;
	String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		System.out.println("new age is " +age);
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("new name is " +name);
		this.name = name;
	}
	public static void main(String[] args) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException, InterruptedException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		Hello hello=new Hello();
		hello.setAge(1);
		hello.setName("hdd");
		mbs.registerMBean(hello, new ObjectName("com.basic:type=hello"));
		Thread.sleep(Long.MAX_VALUE);
	}
}
