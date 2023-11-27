package com.carrentalsystem.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carrentalsystem.main.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	
	
	@Query("select c from Car c where c.id=?1")
	List<Car> getCarByHostJpql(int hid);

	List<Car> findByHostId(int hid);

	List<Car> findBySource(String source);

//	List<Car> findBySourceAndIdNotIn(String source, List<Long> bookedCarIds);

	List<Car> findBySourceAndCarIdNotIn(String source, List<Integer> bookedCarIds);

		

}
