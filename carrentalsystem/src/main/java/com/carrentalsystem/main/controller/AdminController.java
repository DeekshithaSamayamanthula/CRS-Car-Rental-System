package com.carrentalsystem.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.main.dto.AdminDto;
import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Admin;
import com.carrentalsystem.main.model.Car;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.model.Host;
import com.carrentalsystem.main.model.User;
import com.carrentalsystem.main.service.AdminService;
import com.carrentalsystem.main.service.CarService;
import com.carrentalsystem.main.service.CustomerService;
import com.carrentalsystem.main.service.HostService;
import com.carrentalsystem.main.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private HostService hostService;
	@Autowired
	private CarService carService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup") //admin signup 
	public Admin postAdmin(@RequestBody Admin admin) { // ur method is mapped to a URL : api
		
		User user = admin.getUser();
		String password = user.getPassword();
		String encodedpassword = passwordEncoder.encode(password);
		user.setPassword(encodedpassword);
		user.setRole("Admin");
		user = userService.insert(user);
		admin.setUser(user);
		admin = adminService.postAdmin(admin);
		
		return admin;
	}
	/* {
    "adminName":
    "email":
    "phoneNo":
    "user":{
        "username":
        "password":
    }
}*/
	//localhost:9191/admin/getone/10
	@GetMapping("/getone/{id}") //to get the admin by admin id
	public ResponseEntity<?> getone(@PathVariable("id")int id) throws InvalidIdException {
	    Admin admin = adminService.getOne(id);
		return ResponseEntity.ok().body(admin);
	}
	//localhost:9191/admin/getall
	@GetMapping("/getall")// to get all admins
	public List<Admin> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																										// : size & page

		Pageable pageable = PageRequest.of(page, size); // null null
		return adminService.getAll(pageable);
	}
	//localhost:9191/admin/delete/12
	@DeleteMapping("/delete/{id}")//to delete an admin by admin id
	public ResponseEntity<?> deleteAdmin(@PathVariable("id") int id) throws InvalidIdException {
		
		//validate id
		Admin admin = adminService.getOne(id);
		//delete
		adminService.deleteAdmin(admin);
		return ResponseEntity.ok().body("Admin deleted successfully");
	}
	//localhost:9191/admin/update/10
	@PutMapping("/update/{id}") //to update admin details
	public ResponseEntity<?> updateAdmin(@PathVariable("id") int id,
							@RequestBody AdminDto newAdmin) throws InvalidIdException {
		//validate id
		Admin oldAdmin = adminService.getOne(id);
		
		if(newAdmin.getAdminName() != null)
			oldAdmin.setAdminName(newAdmin.getAdminName());
		if(newAdmin.getEmail() != null) 
			oldAdmin.setEmail(newAdmin.getEmail()); 
		if(newAdmin.getPhoneNo() != null)
			oldAdmin.setPhoneNo(newAdmin.getPhoneNo());
		 
		oldAdmin = adminService.postAdmin(oldAdmin); 
		return ResponseEntity.ok().body(oldAdmin);
	}
	//localhost:9191/admin/getall/hosts
		@GetMapping("/getall/hosts")// to get all hosts(admin auth req)
		public List<Host> getAllHosts(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
								   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																											// : size & page

			Pageable pageable = PageRequest.of(page, size); // null null
			return hostService.getAll(pageable);
		}
		//localhost:9191/admin/getall/carsbyhost/2
		@GetMapping("/getall/carsbyhost/{hid}") //all cars posted by one host(admin auth req)
		public ResponseEntity<?> getcarByHost(@PathVariable("hid") int hid) { 
			
			try {
				Host host = hostService.getOne(hid);
				List<Car> list= carService.getcarByHost(hid);
				return ResponseEntity.ok().body(list);
			} catch (InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());

			}
		}
//		localhost:9191/admin/getall/customers
		@GetMapping("/getall/customers")// to get all customers
		public List<Customer> getAllcustomers(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
								   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																											// : size & page

			Pageable pageable = PageRequest.of(page, size); // null null
			return customerService.getAll(pageable);
		}
//		localhost:9191/admin/deletehost/5
		@DeleteMapping("/deletehost/{hid}")// delete host by host id(only if host doesn't have any cars)(admin auth req)
		public ResponseEntity<?> deleteHost(@PathVariable("hid") int hid) throws InvalidIdException {
			
		
			Host host = hostService.getOne(hid);
			
			hostService.deleteHost(host);
			return ResponseEntity.ok().body("Host deleted successfully");
		}
}
