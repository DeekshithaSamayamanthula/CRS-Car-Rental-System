package com.carrentalsystem.main.dto;

import java.time.LocalDate;

public class CustomerCarDto {
	
	private String source;
	private String destination;
	private LocalDate fromDate;
	private LocalDate toDate;
	private double price;
	
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
	public double getPrice() {
		
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CustomerCarDto [source=" + source + ", destination=" + destination + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", price=" + price + "]";
	}

}
