package com.carrentalsystem.main.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
 

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int carId;
	private String carModel;
	private double price;
	private String fuelType;
	private int seating;
	private String insurance;
	private String vehicleNo;
	private String source;
	
// 	private String status;
// public String getStatus() {
// 		return status;
// 	}
// 	public void setStatus(String status) {
// 		this.status = status;
// 	}

public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
@ManyToOne
private Host host;
public int getCarId() {
	return carId;
}
public void setCarId(int carId) {
	this.carId = carId;
}
public String getCarModel() {
	return carModel;
}
public void setCarModel(String carModel) {
	this.carModel = carModel;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}

public String getInsurance() {
	return insurance;
}
public void setInsurance(String insurance) {
	this.insurance = insurance;
}
public String getVehicleNo() {
	return vehicleNo;
}
public void setVehicleNo(String vehicleNo) {
	this.vehicleNo = vehicleNo;
}
public String getFuelType() {
	return fuelType;
}
public void setFuelType(String fuelType) {
	this.fuelType = fuelType;
}
public int getSeating() {
	return seating;
}
public void setSeating(int seating) {
	this.seating = seating;
}

public Host getHost() {
	return host;
}
public void setHost(Host host) {
	this.host = host;
}
@Override
public String toString() {
	return "Car [carId=" + carId + ", carModel=" + carModel + ", price=" + price + ", fuelType=" + fuelType
			+ ", seating=" + seating + ", insurance=" + insurance + ", vehicleNo=" + vehicleNo + ", source=" + source
			+ ", host=" + host + "]";
}


}
