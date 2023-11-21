package com.bankaccount.main.dto;

import java.time.LocalDate;

public class AccountDetailsDto {
	private int id;
	private LocalDate dateOfCreation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(LocalDate dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
}
