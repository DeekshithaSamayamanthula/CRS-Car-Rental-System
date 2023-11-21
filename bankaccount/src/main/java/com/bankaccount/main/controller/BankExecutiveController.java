package com.bankaccount.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccount.main.model.Account;
import com.bankaccount.main.model.BankExecutive;
import com.bankaccount.main.model.User;
import com.bankaccount.main.repository.AccountHolderRepository;
import com.bankaccount.main.service.BankExecutiveService;
import com.bankaccount.main.service.UserService;



@RestController
public class BankExecutiveController {
	@Autowired
	private BankExecutiveService bankexecutiveService;
	@Autowired
	private UserService userService; 
//	@PostMapping("/add/bankexecutive")
//	public ResponseEntity<String> addBankExecutive (@RequestBody BankExecutive bankexecutive) {
//		
//		bankexecutiveService.saveBankExecutive(bankexecutive);
//		return ResponseEntity.ok("Bank Executive saved successfully");
//	}
	@PostMapping("/add/bankexecutive") 
	public BankExecutive postBankExecutive(@RequestBody BankExecutive bankexecutive) { 
    User user = bankexecutive.getUser();
//		String password = user.getPassword();
//		String encodedpassword = passwordEncoder.encode(password);
    	user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());
		user.setRole("EXECUTIVE");
		user = userService.insert(user);
		bankexecutive.setUser(user);
		bankexecutive = bankexecutiveService.postBankExecutive(bankexecutive);		
		return bankexecutive;
}
}
