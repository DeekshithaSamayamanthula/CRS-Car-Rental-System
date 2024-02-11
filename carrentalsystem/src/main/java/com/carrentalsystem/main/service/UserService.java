package com.carrentalsystem.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Customer;
import com.carrentalsystem.main.model.User;
import com.carrentalsystem.main.repository.CustomerRepository;
import com.carrentalsystem.main.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private JavaMailSender mailSender;

	public User postUser(User user) {
		return userRepository.save(user);
	}

	public User insert(User user) {
		return userRepository.save(user);
	}

	public User getOne(int id) throws InvalidIdException {
		Optional<User> optional =  userRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("User ID Invalid");
		}
		return optional.get();
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
		
	}

	
	public void sendEmailOnRegistration(int userId) throws InvalidIdException {
	    Optional<Customer> optional = customerRepository.findById(userId);
	    
	    if (!optional.isPresent()) {
	        throw new InvalidIdException("id not found");
	    }
	    
	    Customer customer = optional.get();
	    // Assuming userRepository.findById method requires a parameter, replace it accordingly
	    // User user = userRepository.findById(customer.getId()).orElse(new User());
	    
	    String subject = "Registration confirmation";
	    String text = "Dear " + customer.getName() + ",\n\n" +
	            "Welcome to Car Rental System – where every journey begins with excitement and comfort!  We are thrilled to have you as our valued member of our community.\n\n" +
	            "Feel free to explore our user-friendly app,named crs.com discover our application comforts and benefits , If you ever need assistance or have queries, our dedicated support team is here to help.\n\n" +
	            "Thank you for choosing our application for your drive. Get ready to explore more!\n\n" +
	            "Warm regards";
	           

	    // Assuming mailSender is an instance of JavaMailSender
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(customer.getEmail());
	    message.setSubject(subject);
	    message.setText(text);
	    mailSender.send(message);
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findByUsername(username);
		System.out.println(user);
		return user;
	}

	public void sendEmailOnBooking(int userId) throws InvalidIdException {
	    Optional<Customer> optional = customerRepository.findById(userId);

	    if (!optional.isPresent()) {
	        throw new InvalidIdException("id not found");
	    }

	    Customer customer = optional.get();
	    // Assuming userRepository.findById method requires a parameter, replace it accordingly
	    // User user = userRepository.findById(customer.getId()).orElse(new User());

	    String subject = "Booking confirmed!!!";
	    String text = "Dear " + customer.getName() + ",\n\n" +
	            "Welcome to CarRent – Booking Successfully Done Get ready to start your rides\n\n" +
	            "Safe travels and happy exploring!\n\n" +
	            "Warm regards,\n\n" +
	            "CarRent Team"; // Add any additional text if needed

	    // Assuming mailSender is an instance of JavaMailSender
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(customer.getEmail());
	    message.setSubject(subject);
	    message.setText(text);
	    mailSender.send(message);
	}
}

	
	
	

