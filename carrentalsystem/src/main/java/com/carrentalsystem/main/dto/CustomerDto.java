package com.carrentalsystem.main.dto;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CustomerDto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String name;
	private String email;
	private int age;
	private String phoneNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", email=" + email + ", age=" + age + ", phoneNo=" + phoneNo + "]";
	}
	

	
}
