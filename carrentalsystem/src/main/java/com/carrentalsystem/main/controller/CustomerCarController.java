package com.carrentalsystem.main.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Car;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.model.CustomerCar;
import com.carrentalsystem.main.service.CarService;
import com.carrentalsystem.main.service.CustomerCarService;
import com.carrentalsystem.main.service.CustomerService;

@RestController
public class CustomerCarController {
	@Autowired
	private CustomerCarService customercarService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarService carService;
	
	/*@PostMapping("/add/{cid}/{carid}")
	public ResponseEntity<?> insertCustomerCar(@PathVariable("cid") int cid,@PathVariable("carid") int carid,
			@RequestBody CustomerCar customerCar) {
		try {
		Customer customer =customerService.getById(cid);
		Car car = carService.getById(carid);
		customerCar.setCar(car);
		customerCar.setCustomer(customer);
		customerCar=customercarService.insert(customerCar);
		return ResponseEntity.ok().body(customerCar);
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}*/
	
}
