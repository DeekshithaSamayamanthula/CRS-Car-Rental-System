package com.carrentalsystem.main.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Car;
import com.carrentalsystem.main.model.CustomerCar;
import com.carrentalsystem.main.repository.CarRepository;
import com.carrentalsystem.main.repository.CustomerCarRepository;

@Service
public class CustomerCarService {
	@Autowired
	private CustomerCarRepository customerCarRepository;
	@Autowired
	private CarRepository carRepository;

	public double price(int carid, LocalDate fromDate, LocalDate toDate) throws InvalidIdException {
		Optional<Car> optional = carRepository.findById(carid);
	    if (!optional.isPresent())
	        throw new InvalidIdException("Car does not exist");

	    double price = 0;
	    long noofdays=ChronoUnit.DAYS.between(fromDate, toDate);
	    if (noofdays <= 1) {
	        return price;
	    } else if (noofdays ==2 ) {
	        return price * 1.10;
	    }
	    else if (noofdays >= 3 && noofdays <=4) {
	        return price * 1.15;
	    }else if (noofdays >= 5 && noofdays <=10) {
	        return price * 1.30;
	    }else if (noofdays >10) {
	        return price * 1.25;
	    }

	    // Default case, though it should never reach here if age is handled properly
	    throw new InvalidIdException("Invalid days");
	}

	public CustomerCar insert(CustomerCar customercar) {
		return customerCarRepository.save(customercar);
	}

	public List<CustomerCar> getMyBookings(int cid) {
		return customerCarRepository.getMyBookings(cid);
	}
	    

}



