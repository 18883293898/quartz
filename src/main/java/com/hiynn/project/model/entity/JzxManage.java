package com.hiynn.project.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JzxManage implements Serializable {

	/**
	 * Fields serialVersionUID: TODO(用一句话描述这个变量表示什么) 
	 */
	private static final long serialVersionUID = 647064751956450297L;
	
	private String id;
	private String name;
	private int height;
	private int age;
	private Date birth;
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
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	 
	
	
	
	
}
