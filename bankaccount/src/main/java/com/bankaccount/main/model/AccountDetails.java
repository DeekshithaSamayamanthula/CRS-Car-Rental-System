package com.bankaccount.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class AccountDetails {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private LocalDate dateOfCreation;
	
	
@ManyToOne
private BankExecutive bankexecutive;
@OneToOne
private AccountHolder account;
@OneToOne
private Account accountholder;
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
public BankExecutive getBankexecutive() {
	return bankexecutive;
}
public void setBankexecutive(BankExecutive bankexecutive) {
	this.bankexecutive = bankexecutive;
}
public AccountHolder getAccount() {
	return account;
}
public void setAccount(AccountHolder account) {
	this.account = account;
}
public Account getAccountholder() {
	return accountholder;
}
public void setAccountholder(Account accountholder) {
	this.accountholder = accountholder;
}
@Override
public String toString() {
	return "AccountDetails [id=" + id + ", dateOfCreation=" + dateOfCreation + ", bankexecutive=" + bankexecutive
			+ ", account=" + account + ", accountholder=" + accountholder + "]";
}

}
