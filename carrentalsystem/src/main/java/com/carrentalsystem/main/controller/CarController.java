package com.carrentalsystem.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.main.dto.CarDto;
import com.carrentalsystem.main.dto.CustomerDto;
import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Car;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.model.Host;
import com.carrentalsystem.main.service.CarService;
import com.carrentalsystem.main.service.HostService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private HostService hostService;
	@Autowired
	private CarService carService;
	
	//localhost:9191/car/post/2
	@PostMapping("/post/{hid}")
	public ResponseEntity<?> postCar(@RequestBody Car car,@PathVariable("hid") int hid) {
		try {
			Host host = hostService.getById(hid);
			car.setHost(host);
			car=carService.insert(car);
			return ResponseEntity.ok().body(car);
			
		}	catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
				
		}
		
		
	}
	/*{
    "carModel":
    "price":
    "fuelType":
    "seating":
    "insurance":
	}*/
	
	
	//localhost:9191/car/getone/9 (no auth)
	@GetMapping("/getone/{carid}")  //get one car by id
	public ResponseEntity<?> getCar(@PathVariable("carid") int carid) {
		try {
			Car car = carService.getCar(carid);
			return ResponseEntity.ok().body(car);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	//localhost:9191/car/getall (no auth )
	@GetMapping("/getall")  //to get all cars
	public List<Car> getAllCar(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10000000") Integer size) {
		Pageable pageable = PageRequest.of(page, size); // null null
		return carService.getAll(pageable);
	}
	//localhost:9191/car/update/5/6
	@PutMapping("/update/{hid}/{carid}")//to update car details by host id and car id
	public ResponseEntity<?> updateCar(@PathVariable("carid") int carid,@PathVariable("hid") int hid,
			@RequestBody CarDto carDto){
		try {
			Car car = carService.getById(carid);
			if(carDto.getCarModel()!=null)
				car.setCarModel(carDto.getCarModel());
			if(carDto.getPrice()!=0)
				car.setPrice(carDto.getPrice());		
			if(carDto.getFuelType()!=null)
				car.setFuelType(carDto.getFuelType());
			if(carDto.getSeating()!=0)
				car.setSeating(carDto.getSeating());
			if(carDto.getInsurance()!=null)
				car.setInsurance(carDto.getInsurance());
			car=carService.postCar(car);
			return ResponseEntity.ok().body(car);	
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	//localhost:9191/car/getall/2
	@GetMapping("/getall/{hid}") //all cars posted by one host
	public ResponseEntity<?> getcarByHost(@PathVariable("hid") int hid) { 
		
		try {
			Host host = hostService.getOne(hid);
			List<Car> list= carService.getcarByHost(hid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
}