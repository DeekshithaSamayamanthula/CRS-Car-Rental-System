package com.bankaccount.main.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccount.main.dto.AccountDetailsDto;
import com.bankaccount.main.model.Account;
import com.bankaccount.main.model.AccountDetails;
import com.bankaccount.main.model.AccountHolder;

import com.bankaccount.main.service.AccountDetailsService;
import com.bankaccount.main.service.AccountHolderService;
import com.bankaccount.main.service.AccountService;





@RestController
public class AccountDetailsController {
	@Autowired
	private AccountDetailsService accountDetailsService; 
	@Autowired
	private AccountHolderService accountHolderService;
	@Autowired
	private AccountService accountService;
	
	
	@PostMapping("/accdetails/{ahid}/{aid}")
	public ResponseEntity<?> accountDetails(@PathVariable("ahid") int ahid, @PathVariable("aid") int aid,
			@RequestBody List<AccountDetailsDto> accountDetailsDtoList) {
		try {

			AccountHolder accountholder = accountHolderService.getAccountHolder(ahid);
			Account account = accountService.getById(aid);
				
			List<AccountDetails> accountDetailsList = new ArrayList<>();
			
			for (AccountDetailsDto accountDetailsDto : accountDetailsDtoList) {
				AccountDetails accountDetails = new AccountDetails();
				accountDetails.setAccountholder(accountholder);
				accountDetails.setAccount(account);
				
				accountDetails.setDateOfCreation(LocalDate.now());
			
				
				
				
			
				accountDetailsList.add(accountdetailsService.insert(accountdetails));
				
			}

			Map<String, Object> response = new HashMap<>();
			response.put("bookedCars", bookedCars);
			response.put("totalPrice", totalPrice);

			return ResponseEntity.ok().body(response);
		} catch (InvalidIdException e) { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
