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
import com.carrentalsystem.main.model.Host;
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

	    double price=optional.get().getPrice();
	    System.err.println("getting price in service:"+price);
//	    return price;
	    long noofdays=ChronoUnit.DAYS.between(fromDate, toDate);
	    System.err.println("noofdays:"+noofdays);
	    if (noofdays <= 1) {
	        return price;
	    } else if (noofdays ==2 ) {
	        return price * 1.3;
	    }
	    else if (noofdays >= 3 && noofdays <=4) {
	        return price * 1.5;
	    }else if (noofdays >= 5 && noofdays <=10) {
	        return price * 1.7;
	    }else if (noofdays >10) {
	        return price * 1.6;
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

	public List<CustomerCar> getcustomers(int carid) {
		// TODO Auto-generated method stub
		return  customerCarRepository.getBycarId(carid);
	}

	public CustomerCar getById(int bid) throws InvalidIdException {
		
		Optional<CustomerCar> optional = customerCarRepository.findById(bid);
		if(!optional.isPresent())
			throw new InvalidIdException("booking id Invalid");
		return optional.get();
	}

	public CustomerCar updatebookingdetails(CustomerCar customercar) {
		// TODO Auto-generated method stub
		return customerCarRepository.save(customercar);
	}

	public List<CustomerCar> getcustomers(int carid, LocalDate date) {
		// TODO Auto-generated method stub
		return customerCarRepository.getBycarIdandDate(carid,date);
	}



//	public List<CustomerCar> getcarbookingsByHostId(int hid) {
//		// TODO Auto-generated method stub
//		return  customerCarRepository.getByhostId(hid);
//	}
	    

}



