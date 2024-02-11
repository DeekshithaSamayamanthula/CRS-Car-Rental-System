package com.carrentalsystem.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.main.dto.CustomerCarDto;
import com.carrentalsystem.main.dto.CustomerDto;
import com.carrentalsystem.main.dto.HostDto;
import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Car;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.model.CustomerCar;
import com.carrentalsystem.main.model.Host;
import com.carrentalsystem.main.service.CarService;
import com.carrentalsystem.main.service.CustomerCarService;
import com.carrentalsystem.main.service.CustomerService;
import com.carrentalsystem.main.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class CustomerCarController {
	@Autowired
	private CustomerCarService customercarService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarService carService;
	
	@Autowired
	private UserService userService;
	
	//localhost:9191/bookcar/8/112
	@PostMapping("/bookcar/{cid}/{carid}")
	public ResponseEntity<?> bookcar(@PathVariable("cid") int cid, @PathVariable("carid") int carid,
			@RequestBody List<CustomerCarDto> custCarDtoList) {
		try {

			Customer customer = customerService.getCustomer(cid);
			Car car = carService.getById(carid);
//			var status=car.getStatus();
				
		//	if(car.getStatus() != "booked") {
				System.err.println("book car api");
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
				
				customercar
						.setPrice(customercarService.price(carid, custCarDto.getFromDate(), custCarDto.getToDate()));
				
				totalPrice = totalPrice + (customercar.getPrice());
				System.err.println("after price");
		
				bookedCars.add(customercarService.insert(customercar));
				// customercar.setStatus("booked");
				userService.sendEmailOnBooking(cid);
				// car.setStatus("booked");
				System.err.println("insert(customercar)");
			}

			Map<String, Object> response = new HashMap<>();
			response.put("bookedCars", bookedCars);
			response.put("totalPrice", totalPrice);

			return ResponseEntity.ok().body(response);
//		} 
		// else {
		// 	return ResponseEntity.badRequest().body("car not available");
		// }  
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
//	localhost:9191/customers/22
	@GetMapping("/customers/{carid}") // get booked customers by carid 
	public ResponseEntity<?> getcustomers(@PathVariable("carid") int carid) {

		try {
			Car car = carService.getById(carid);
			List<CustomerCar> list = customercarService.getcustomers(carid);
			return ResponseEntity.ok().body(list);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//	localhost:9191/update/bookingdetails/96
	@PutMapping("/update/bookingdetails/{bid}")// to update booking details by booking id
	public ResponseEntity<?> updatebookingdetails(@PathVariable("bid") int bid,@RequestBody CustomerCarDto
			customercarDto){
		try {
			CustomerCar customercar = customercarService.getById(bid);
			if(customercarDto.getSource()!=null)
				customercar.setSource(customercarDto.getSource());
			if(customercarDto.getDestination()!=null)
				customercar.setDestination(customercarDto.getDestination());
			if(customercarDto.getFromDate()!=null)
				customercar.setFromDate(customercarDto.getFromDate());
			if(customercarDto.getToDate()!=null)
				customercar.setToDate(customercarDto.getToDate());
			if(customercarDto.getPrice()!=0)
				customercar.setPrice(customercarDto.getPrice());
			customercar= customercarService.updatebookingdetails(customercar);
			return ResponseEntity.ok().body(customercar);	
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
//	localhost:9191/customers/22/2023-11-01
	@GetMapping("/customers/{carid}/{date}") // get booked customers by carid ,date
	public ResponseEntity<?> getcustomers(@PathVariable("carid") int carid, 
			@PathVariable("date") LocalDate date) {

		try {
			Car car = carService.getById(carid);
			List<CustomerCar> list = customercarService.getcustomers(carid,date);
			return ResponseEntity.ok().body(list);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}