package com.carrentalsystem.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Car;
import com.carrentalsystem.main.model.CustomerCar;
import com.carrentalsystem.main.repository.CarRepository;
import com.carrentalsystem.main.repository.CustomerCarRepository;

@Service
public class CarService {
@Autowired
private CarRepository carRepository;
@Autowired
private CustomerCarRepository customerCarRepository;
	public Car insert(Car car) {
		return carRepository.save(car);
	}
	public Car getById(int carid) throws InvalidIdException {
		Optional<Car>optional=carRepository.findById(carid);
		if(!optional.isPresent())
			throw new InvalidIdException("car id is incorrect");
		return optional.get();
	}
	public Car postCar(Car car) {
		// TODO Auto-generated method stub
		return carRepository.save(car);
	}
	public Car getCar(int carid) throws InvalidIdException {
		Optional<Car> optional = carRepository.findById(carid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Car ID invalid");
		}
		return optional.get();
	}
	public List<Car> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return carRepository.findAll(pageable).getContent();
	}
	
	public List<Car> getcarByHost(int hid) {
		// TODO Auto-generated method stub
		return carRepository.findByHostId(hid);
	}
	public void deleteCar(Car car) {
		// TODO Auto-generated method stub
		carRepository.delete(car);
	}
	public Car getOne(int carid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Car> optional =  carRepository.findById(carid);
		if(!optional.isPresent()){
			throw new InvalidIdException("Car ID Invalid");
		}
		return optional.get();
	}
	public List<CustomerCar> getBookingsByHostId(int hostId) {
		 return customerCarRepository.findByCar_Host_Id(hostId);
	}
	public List<Car> getCarsBySource(String source) {
		return carRepository.findBySource(source);
	}
	public List<Car> getAvailableCars(String source) {
		List<CustomerCar> bookedCars = customerCarRepository.findBySource(source);
        List<Integer> bookedCarIds = bookedCars.stream().map(customerCar -> (Integer) customerCar.getCar().getCarId()).collect(Collectors.toList());
        System.err.println("available api service"+  bookedCarIds);
        if (bookedCarIds.isEmpty()) {
            // If no cars are booked, return all cars from the specified source
            return carRepository.findBySource(source);
        } else {
            // If cars are booked, return available cars from the specified source
            return carRepository.findBySourceAndCarIdNotIn(source, bookedCarIds);
        }
    }



	
}
