package com.carrentalsystem.main.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class CustomerCar { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String source;
	private String destination;
	private LocalDate fromDate;
	private LocalDate toDate;
	private double price;
// 	private String status;
// public String getStatus() {
// 		return status;
// 	}

// 	public void setStatus(String status) {
// 		this.status = status;
// 	}

@OneToOne
private Customer customer;

@OneToOne
private Car car;
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public String getDestination() {
	return destination;
}

public void setDestination(String destination) {
	this.destination = destination;
}

public LocalDate getFromDate() {
	return fromDate;
}

public void setFromDate(LocalDate fromDate) {
	this.fromDate = fromDate;
}

public LocalDate getToDate() {
	return toDate;
}

public void setToDate(LocalDate toDate) {
	this.toDate = toDate;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public Car getCar() {
	return car;
}

public void setCar(Car car) {
	this.car = car;
}
public double getPrice() {
//	System.err.println("getting price"+getPrice());
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

@Override
public String toString() {
	return "CustomerCar [id=" + id + ", source=" + source + ", destination=" + destination + ", fromDate=" + fromDate
			+ ", toDate=" + toDate + ", price=" + price + ", customer=" + customer + ", car="
			+ car + "]";
}





}
