package com.carrentalsystem.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carrentalsystem.main.model.CustomerCar;

public interface CustomerCarRepository extends JpaRepository<CustomerCar, Integer> {
	@Query("select cc from CustomerCar cc where cc.customer.id =?1")
	List<CustomerCar> getMyBookings(int cid);
	
	@Query("select cc from CustomerCar cc where cc.car.id =?1")
	List<CustomerCar> getBycarId(int carid);

	@Query("select cc from CustomerCar cc where cc.car.id =?1")
	List<CustomerCar> getByhostId(int hid);

	List<CustomerCar> findByCar_Host_Id(int hostId);

}
