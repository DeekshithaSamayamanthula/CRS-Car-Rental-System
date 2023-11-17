package com.carrentalsystem.main.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.carrentalsystem.main.model.User;
import com.carrentalsystem.main.service.UserService;

public class AuthController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/login")
	public User login(Principal  principal) { 
		String username = principal.getName();
		User user = (User)userService.loadUserByUsername(username);
		return user; 
	}
}
