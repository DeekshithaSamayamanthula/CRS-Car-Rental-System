package com.bankaccount.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.main.model.BankExecutive;
import com.bankaccount.main.repository.BankExecutiveRepository;
@Service
public class BankExecutiveService {
	@Autowired
	private BankExecutiveRepository bankexecutiveRepository;
	public void saveBankExecutive(BankExecutive bankexecutive) {
		// TODO Auto-generated method stub
		bankexecutiveRepository.save(bankexecutive);
	}
	public BankExecutive postBankExecutive(BankExecutive bankexecutive) {
		// TODO Auto-generated method stub
		return bankexecutiveRepository.save(bankexecutive);
	}

}
