package com.carrentalsystem.main.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.main.model.User;
import com.carrentalsystem.main.service.UserService;
@RestController

@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/login")
	public User login(Principal  principal) { 
		String username = principal.getName();
		User user = (User)userService.loadUserByUsername(username);
		
		return user; 
	}
}
