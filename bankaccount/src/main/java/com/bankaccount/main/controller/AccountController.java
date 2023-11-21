package com.bankaccount.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccount.main.model.Account;

import com.bankaccount.main.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	@PostMapping("/add/account")
	public ResponseEntity<String> addAccount (@RequestBody Account account) {
		accountService.saveAccount(account);
		return ResponseEntity.ok("Account saved successfully");
	}
	
}
