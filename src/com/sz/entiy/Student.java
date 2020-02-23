package com.sz.entiy;

import java.util.Date;

public class Student {
	private String id;
	private String name;
	private String pwd;
	private String clazz;
	private Date enterdate;
	private int age;
	private int sex;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getEnterdate() {
		return enterdate;
	}

	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public Student()
	{
		super();
	}
	public Student(String id, String name, String pwd, 
			int age, int sex, Date enterdate,String clazz) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.enterdate = enterdate;
		this.age = age;
		this.sex = sex;
		this.clazz = clazz;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", enterdate=" + enterdate + ", age=" + age + ", sex=" + sex
				+ ", clazz=" + clazz + "]";
	}
	
}
