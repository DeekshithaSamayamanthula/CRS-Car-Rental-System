package com.carrentalsystem.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentalsystem.main.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
