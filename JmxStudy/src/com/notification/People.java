package com.notification;

import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;

import com.basic.Hello;

public class People extends NotificationBroadcasterSupport implements PeopleMBean {
 int age;
 String name;
 private AtomicLong sequenceNumber = new AtomicLong(1);  
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		System.out.println("new age is " +age);
		int oldage=this.age;
		this.age = age;
		super.sendNotification(new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(), System.currentTimeMillis(), AttributeChangeNotification.ATTRIBUTE_CHANGE, "age is changed", "java.lang.int", oldage, age));
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("new name is " +name);
		String oldname=this.name;
		this.name = name;
		super.sendNotification(new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(), System.currentTimeMillis(), AttributeChangeNotification.ATTRIBUTE_CHANGE, "name is changed", "java.lang.String", oldname, name));
	}
	
	public static void main(String[] args) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException, InterruptedException, InstanceNotFoundException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
//		ManagementFactory.getMemoryManagerMXBeans()
		People people=new People();
		people.setAge(1);
		people.setName("hdd");
		
		ObjectName objectName = new ObjectName("com.basic:type=people");
		mbs.registerMBean(people, objectName);
		mbs.addNotificationListener(objectName, new ServerConfigureNotificationListener(),null,null);
		int i=1;
		while(true){
			people.setAge(i);
			i++;
			Thread.sleep(1000);
		}
//		Thread.sleep(Long.MAX_VALUE);
	}
}
