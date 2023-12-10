package com.carrentalsystem.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentalsystem.main.model.Host;

public interface HostRepository extends JpaRepository<Host, Integer> {

	boolean existsByHostEmail(String email);

}
