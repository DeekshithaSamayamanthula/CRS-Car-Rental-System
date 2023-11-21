package com.bankaccount.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankaccount.main.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {

}
