package com.bankaccount.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaccount.main.model.User;
import com.bankaccount.main.repository.BankExecutiveRepository;
import com.bankaccount.main.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User insert(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

}
