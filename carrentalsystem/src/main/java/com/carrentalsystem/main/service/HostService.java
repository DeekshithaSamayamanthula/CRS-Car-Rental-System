package com.carrentalsystem.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carrentalsystem.main.exception.InvalidIdException;
import com.carrentalsystem.main.model.Admin;
import com.carrentalsystem.main.model.Host;
import com.carrentalsystem.main.repository.HostRepository;

@Service
public class HostService {
@Autowired
private HostRepository hostRepository;
	public Host postHost(Host host) {
		// TODO Auto-generated method stub
		return hostRepository.save(host);
	}
	public Host getById(int hid) throws InvalidIdException {
		Optional<Host> optional =hostRepository.findById(hid);
		if(!optional.isPresent())
			throw new InvalidIdException("Host id Invalid");
		return optional.get();
	}
	public Host getOne(int id) throws InvalidIdException {
		Optional<Host> optional=hostRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Host ID Invalid");
		}
		return optional.get();
	}
	public void deleteHost(Host host) {
		// TODO Auto-generated method stub
		hostRepository.delete(host);
	}
	public Host getHost(int hid) throws InvalidIdException {
		Optional<Host> optional = hostRepository.findById(hid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Host ID invalid");
		}
		return optional.get();
	}
	public List<Host> getAll(Pageable pageable) {
		return hostRepository.findAll(pageable).getContent();
	}
	public boolean existsByEmail(String email) {
		 return hostRepository.existsByHostEmail(email);
	}

}
