package com.carrentalsystem.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentalsystem.main.model.CustomerCar;

public interface CustomerCarRepository extends JpaRepository<CustomerCar, Integer> {

}
