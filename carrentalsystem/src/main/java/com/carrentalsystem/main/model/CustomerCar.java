package com.carrentalsystem.main.model;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class CustomerCar { 
	@Id
	private String source;
	private String destination;
	private LocalDate fromDate;
	private LocalDate toDate;


@OneToOne
private Customer customer;

@OneToOne
private Car car;

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

@Override
public String toString() {
	return "CustomerCar [source=" + source + ", destination=" + destination + ", fromDate=" + fromDate + ", toDate="
			+ toDate + ", customer=" + customer + ", car=" + car + "]";
}

}
