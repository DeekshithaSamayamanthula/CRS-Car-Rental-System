package com.bankaccount.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankaccount.main.model.Account;



public interface AccountRepository extends JpaRepository<Account, Integer>{

}
