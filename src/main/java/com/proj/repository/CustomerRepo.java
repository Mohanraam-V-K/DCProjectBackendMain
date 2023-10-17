package com.proj.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.Customer;


public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByCustomerId(UUID uuid);

    Customer findByEmail(String email);

    Customer findByEmailAndPassword(String email,String password);
}