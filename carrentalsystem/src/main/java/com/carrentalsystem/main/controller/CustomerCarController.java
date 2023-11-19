package com.carrentalsystem.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.main.dto.CustomerCarDto;
import com.carrentalsystem.main.dto.CustomerDto;
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
	
	@PostMapping("/bookcar/{cid}/{carid}")
	public ResponseEntity<?> bookcar(@PathVariable("cid") int cid, @PathVariable("carid") int carid,
			@RequestBody List<CustomerCarDto> custCarDtoList) {
		try {

			Customer customer = customerService.getCustomer(cid);
			Car car = carService.getById(carid);
			List<CustomerCar> bookedCars = new ArrayList<>();
			double totalPrice = 0;
			for (CustomerCarDto custCarDto : custCarDtoList) {
				CustomerCar customercar = new CustomerCar();
				customercar.setCustomer(customer);
				customercar.setCar(car);
				customercar.setSource(custCarDto.getSource());
				customercar.setDestination(custCarDto.getDestination());
				customercar.setFromDate(custCarDto.getFromDate());
			
				customercar.setToDate(custCarDto.getToDate());
				System.err.println("setting price");
				customercar
						.setPrice(customercarService.price(carid, custCarDto.getFromDate(), custCarDto.getToDate()));
				
				totalPrice = totalPrice + (customercar.getPrice());
				System.err.println("getting price1:"+totalPrice);
			
				bookedCars.add(customercarService.insert(customercar));
				
			}

			Map<String, Object> response = new HashMap<>();
			response.put("bookedCars", bookedCars);
			response.put("totalPrice", totalPrice);

			return ResponseEntity.ok().body(response);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

//	localhost:9191/customer/bookings/8
	@GetMapping("/customer/bookings/{cid}") // get customer bookings by customer id
	public ResponseEntity<?> getYourBookings(@PathVariable("cid") int cid) {

		try {
			Customer customer = customerService.getCustomer(cid);
			List<CustomerCar> list = customercarService.getMyBookings(cid);
			return ResponseEntity.ok().body(list);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
