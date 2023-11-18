package com.carrentalsystem.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentalsystem.main.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
