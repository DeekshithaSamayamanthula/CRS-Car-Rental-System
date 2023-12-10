package com.carrentalsystem.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentalsystem.main.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	boolean existsByEmail(String email);

}
