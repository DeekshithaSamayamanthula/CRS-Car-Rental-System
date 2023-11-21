package com.bankaccount.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.bankaccount.main.model.AccountHolder;
import com.bankaccount.main.model.User;
import com.bankaccount.main.service.AccountHolderService;


@RestController
public class AccountHolderController {
	@Autowired
	private AccountHolderService accountHolderService;

	@PostMapping("/add/accountholder")
	public ResponseEntity<String> addAccountHolder (@RequestBody AccountHolder accountholder) {
		accountHolderService.saveAccountHolder(accountholder);
		return ResponseEntity.ok("Account Holder saved successfully");
	}
	
}
