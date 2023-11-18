package com.carrentalsystem.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.model.CustomerCar;
import com.carrentalsystem.main.repository.CustomerCarRepository;

@Service
public class CustomerCarService {
	@Autowired
	private CustomerCarRepository customerCarRepository;


}
