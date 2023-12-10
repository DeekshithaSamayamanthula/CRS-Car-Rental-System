package com.carrentalsystem.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Admin;
import com.carrentalsystem.main.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired 
	private AdminRepository adminRepository;
	public Admin postAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	public Admin getOne(int id) throws InvalidIdException {
		Optional<Admin> optional=adminRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Admin ID Invalid");
		}
		return optional.get();
	}
	public List<Admin> getAll(Pageable pageable) {
		return adminRepository.findAll(pageable).getContent();
	}
	public void deleteAdmin(Admin admin) {
		adminRepository.delete(admin);
	}
	public boolean existsByEmail(String email) {
		 return adminRepository.existsByEmail(email);
	}

}
