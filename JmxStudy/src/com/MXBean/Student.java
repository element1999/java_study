package com.MXBean;

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

import com.notification.People;
import com.notification.ServerConfigureNotificationListener;

public class Student  extends NotificationBroadcasterSupport implements StudentMXBean {
	int age;
	 String name;
	 School school;
	 @Override
	public School getSchool() {
		return school;
	}
	@Override
	public void setSchool(School school) {
		School old=this.school;
		School newone=this.school;
		this.school = school;
		super.sendNotification(new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(), System.currentTimeMillis(), AttributeChangeNotification.ATTRIBUTE_CHANGE, "school is changed", "com.MXBean.School", old, newone));
		
	}

	private AtomicLong sequenceNumber = new AtomicLong(1);  
		/* (non-Javadoc)
		 * @see com.MXBean.StudentMBean#getAge()
		 */
		@Override
		public int getAge() {
			return age;
		}
		/* (non-Javadoc)
		 * @see com.MXBean.StudentMBean#setAge(int)
		 */
		@Override
		public void setAge(int age) {
			System.out.println("new age is " +age);
			int oldage=this.age;
			this.age = age;
			super.sendNotification(new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(), System.currentTimeMillis(), AttributeChangeNotification.ATTRIBUTE_CHANGE, "age is changed", "java.lang.int", oldage, age));
		}
		/* (non-Javadoc)
		 * @see com.MXBean.StudentMBean#getName()
		 */
		@Override
		public String getName() {
			return name;
		}
		/* (non-Javadoc)
		 * @see com.MXBean.StudentMBean#setName(java.lang.String)
		 */
		@Override
		public void setName(String name) {
			System.out.println("new name is " +name);
			String oldname=this.name;
			this.name = name;
			super.sendNotification(new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(), System.currentTimeMillis(), AttributeChangeNotification.ATTRIBUTE_CHANGE, "name is changed", "java.lang.String", oldname, name));
		}
		
		public static void main(String[] args) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException, InterruptedException, InstanceNotFoundException {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			StudentMXBean student=new Student();
			student.setAge(1);
			student.setName("hdd");
			student.setSchool(new School("xixiao", 100));
			ObjectName objectName = new ObjectName("com.basic:type=student");
			mbs.registerMBean(student, objectName);
			mbs.addNotificationListener(objectName, new ServerConfigureNotificationListener(),null,null);
			int i=1;
//			while(true){
//				student.setAge(i);
//				i++;
//				Thread.sleep(1000);
//			}
			Thread.sleep(Long.MAX_VALUE);
		}
}
