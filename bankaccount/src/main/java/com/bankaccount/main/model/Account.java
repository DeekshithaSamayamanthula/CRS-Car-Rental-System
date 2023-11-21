package com.bankaccount.main.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bankaccount.main.enums.Type;



@Entity
public class Account {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private double rateOfInterest;
	private String balance;
	
	@Enumerated(EnumType.STRING)	
	private Type type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", rateOfInterest=" + rateOfInterest + ", balance=" + balance + ", type=" + type
				+ "]";
	}
	
}
