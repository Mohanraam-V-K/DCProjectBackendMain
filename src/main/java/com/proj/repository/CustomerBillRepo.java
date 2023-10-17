package com.proj.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.CustomerBill;

public interface CustomerBillRepo extends JpaRepository<CustomerBill,UUID> {
	CustomerBill findByCustomerId(String customerId);
//    CustomerBill findByemail(String email, Double double1, String string);

	  CustomerBill findByemail(String email);
}