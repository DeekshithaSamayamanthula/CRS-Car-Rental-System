package com.carrentalsystem.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.User;
import com.carrentalsystem.main.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findByUsername(username);
		System.out.println(user);
		return user;
	}
	
	
	

}
