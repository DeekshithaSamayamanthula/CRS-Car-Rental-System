package com.bankaccount.main.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.main.exception.InvalidIdException;
import com.bankaccount.main.model.AccountHolder;
import com.bankaccount.main.repository.AccountHolderRepository;

@Service
public class AccountHolderService {
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	public void saveAccountHolder(AccountHolder accountholder) {
		// TODO Auto-generated method stub
		accountHolderRepository.save(accountholder);
	}
	public AccountHolder getAccountHolder(int ahid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<AccountHolder> optional = accountHolderRepository.findById(ahid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Account holder ID invalid");
		}
		return optional.get();
	}

}
