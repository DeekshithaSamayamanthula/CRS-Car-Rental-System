package com.bankaccount.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bankaccount.main.model.BankExecutive;

public interface BankExecutiveRepository extends JpaRepository<BankExecutive, Integer>{

}
