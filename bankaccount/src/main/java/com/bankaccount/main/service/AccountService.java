package com.bankaccount.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.main.exception.InvalidIdException;
import com.bankaccount.main.model.Account;
import com.bankaccount.main.model.AccountHolder;
import com.bankaccount.main.repository.AccountHolderRepository;
import com.bankaccount.main.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		accountRepository.save(account);
	}

	public Account getById(int aid) throws InvalidIdException {
		Optional<Account>optional=accountRepository.findById(aid);
		if(!optional.isPresent())
			throw new InvalidIdException("account id is incorrect");
		return optional.get();
	}

	

}
