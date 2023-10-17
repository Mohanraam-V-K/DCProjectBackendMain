package com.proj.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {

    public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID customerId;
 
    private String customerName;
  
    private String email;
  
    private String password;
    
    private String gender;
    
    private String address;
    
    public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	private String status="active";
    
    public UUID getCustomerId() {
		return customerId;
	}


	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public Customer(UUID customerId, String customerName, String email, String password, String gender, String address,
			String phoneNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	private String phoneNumber;
    
}