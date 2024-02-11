package com.carrentalsystem.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.main.dto.CustomerDto;
import com.carrentalsystem.main.enums.Role;
import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Admin;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.model.Host;
import com.carrentalsystem.main.model.User;
import com.carrentalsystem.main.service.CustomerService;
import com.carrentalsystem.main.service.UserService;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = {"http://localhost:3000"}) 
@Configuration
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private Logger logger;
	//localhost:9191/customer/signup
	@PostMapping("/signup")
	public ResponseEntity<Object> postAdmin(@RequestBody Customer customer) {
	    // Check if the email already exists in the Customer table
	    String email = customer.getEmail();
	    if (customerService.existsByEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }

	    // If the email doesn't exist, proceed with the signup process
	    User user = customer.getUser();
	    String password = user.getPassword();
	    String encodedpassword = passwordEncoder.encode(password);
	    user.setPassword(encodedpassword);
	    user.setRole(Role.Customer);
	    user = userService.insert(user);
	    customer.setUser(user);
	    customerService.postCustomer(customer);
	    logger.info("Customer signed up: {}",customer.getName());
	    
	    int uid=customer.getId();
		try {
			userService.sendEmailOnRegistration(uid);
			} catch (InvalidIdException e) { e.printStackTrace();
			}
	  

	    // Return a success message along with the created customer
	    return ResponseEntity.status(HttpStatus.OK).body(customer);
	}

/*{
    "name":"",
    "email":"",
    "age":"",
    "phoneNo":"",
    "user":{
        "username":"",
        "password":""
    }
} */
	//localhost:9191/customer/getone/8
	@GetMapping("/getone/{id}") //to get the customer by customer id
	public ResponseEntity<?> getone(@PathVariable("id")int id) throws InvalidIdException {
	    Customer customer = customerService.getOne(id);
		return ResponseEntity.ok().body(customer);
	}
	//localhost:9191/customer/getall
	@GetMapping("/getall")// to get all customers
	public List<Customer> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																										// : size & page

		Pageable pageable = PageRequest.of(page, size); // null null
		return customerService.getAll(pageable);
	}
	
	//localhost:9191/customer/update/8
	@PutMapping("/update/{cid}")// to update customer details
	public ResponseEntity<?> updateCustomer(@PathVariable("cid") int cid,@RequestBody CustomerDto customerDto){
		try {
			Customer customer = customerService.getById(cid);
			if(customerDto.getName()!=null)
				customer.setName(customerDto.getName());
			if(customerDto.getEmail()!=null)
				customer.setEmail(customerDto.getEmail());
			if(customerDto.getAge()!=0)
				customer.setAge(customerDto.getAge());
			if(customerDto.getPhoneNo()!=null)
				customer.setPhoneNo(customerDto.getPhoneNo());
			customer=customerService.postCustomer(customer);
			return ResponseEntity.ok().body(customer);	
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	}