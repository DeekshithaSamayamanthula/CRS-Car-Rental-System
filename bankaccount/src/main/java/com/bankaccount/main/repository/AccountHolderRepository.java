package com.bankaccount.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankaccount.main.model.AccountHolder;



public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer>{



}
