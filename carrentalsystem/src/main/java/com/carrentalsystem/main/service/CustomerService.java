package com.carrentalsystem.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Admin;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	

	public Customer postCustomer(Customer customer) {
		return customerRepository.save(customer);
	}



	public Customer getById(int cid) throws InvalidIdException {
		Optional<Customer> optional=customerRepository.findById(cid);
		if(!optional.isPresent())
			throw new InvalidIdException("customer id invalid");
				return optional.get();
	}



	public Customer getOne(int id) throws InvalidIdException {
		Optional<Customer> optional=customerRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Customer ID Invalid");
		}
		return optional.get();
	}



	public List<Customer> getAll(Pageable pageable) {
		return customerRepository.findAll(pageable).getContent();
	}



	public Customer getCustomer(int cid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById(cid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Customer ID invalid");
		}
		return optional.get();
	}



	public boolean existsByEmail(String email) {
				return customerRepository.existsByEmail(email);
	}



	

}
