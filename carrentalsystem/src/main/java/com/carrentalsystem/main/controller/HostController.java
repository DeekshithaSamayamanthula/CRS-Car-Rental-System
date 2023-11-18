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

import com.carrentalsystem.main.dto.HostDto;
import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Admin;
import com.carrentalsystem.main.model.Car;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.model.Host;
import com.carrentalsystem.main.model.User;
import com.carrentalsystem.main.service.CarService;
import com.carrentalsystem.main.service.HostService;
import com.carrentalsystem.main.service.UserService;

@RestController
@RequestMapping("/host")
public class HostController {
	@Autowired
	private HostService hostService;
	@Autowired
	private CarService carService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")//host signup
	public Host postAdmin(@RequestBody Host host) { 
    User user = host.getUser();
		String password = user.getPassword();
		String encodedpassword = passwordEncoder.encode(password);
		user.setPassword(encodedpassword);
		user.setRole("Host");
		user = userService.insert(user);
		host.setUser(user);
		host = hostService.postHost(host);		
		return host;
}
	/*{
    "hostEmail":"",
    "hostName":"",
    "hostContact":"",
    "user":{
        "username":"",
        "password":""
    }
} */
	//localhost:9191/host/getone/2
	@GetMapping("/getone/{id}") //to get the host by host id
	public ResponseEntity<?> getone(@PathVariable("id")int id) throws InvalidIdException {
	    Host host = hostService.getOne(id);
		return ResponseEntity.ok().body(host);
	}
	//localhost:9191/host/getall
	@GetMapping("/getall")// to get all hosts
	public List<Host> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																										// : size & page

		Pageable pageable = PageRequest.of(page, size); // null null
		return hostService.getAll(pageable);
	}
	//localhost:9191/host/update/5
	@PutMapping("/update/{hid}")// to update host details by host id
	public ResponseEntity<?> updateHost(@PathVariable("hid") int hid,@RequestBody HostDto hostDto){
		try {
			Host host = hostService.getById(hid);
			if(hostDto.getHostEmail()!=null)
				host.setHostEmail(hostDto.getHostEmail());
			if(hostDto.getHostName()!=null)
				host.setHostName(hostDto.getHostName());
			if(hostDto.getHostContact()!=null)
				host.setHostContact(hostDto.getHostContact());
			host= hostService.postHost(host);
			return ResponseEntity.ok().body(host);	
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	//localhost:9191/host/delete/18
	@DeleteMapping("/delete/{id}")// delete host by host id
	public ResponseEntity<?> deleteHost(@PathVariable("id") int id) throws InvalidIdException {
		
	
		Host host = hostService.getOne(id);
		
		hostService.deleteHost(host);
		return ResponseEntity.ok().body("Host deleted successfully");
	}
	//localhost:9191/host/delete/2/13
	@DeleteMapping("/delete/{hid}/{carid}")// to delete a car by host id and car id
	public ResponseEntity<?> deleteCar(@PathVariable("hid") int hid,@PathVariable("carid") int carid) {
		
		try {
			//validate id
			Host host = hostService.getOne(hid);
			Car car=carService.getOne(carid);
			//delete
			carService.deleteCar(car);
			return ResponseEntity.ok().body("Car Deleted Successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}