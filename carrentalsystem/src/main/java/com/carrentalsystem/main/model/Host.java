package com.carrentalsystem.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Host {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; 
	private String hostEmail;	
	private String hostName;
	private String hostContact;
	@OneToOne
	private User user; 
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHostEmail() {
		return hostEmail;
	}
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}

	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostContact() {
		return hostContact;
	}
	public void setHostContact(String hostContact) {
		this.hostContact = hostContact;
	}
	@Override
	public String toString() {
		return "Host [id=" + id + ", hostEmail=" + hostEmail + ", hostName=" + hostName + ", hostContact=" + hostContact
				+ ", user=" + user + "]";
	}
	
}
