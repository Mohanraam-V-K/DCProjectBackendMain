package com.proj.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.Login;

public interface LoginRepo extends JpaRepository<Login,Long> {
	 Login findByUsernameAndPassword(String username, String password);

	    Login findByUserId(UUID userId);

	    Login findByUsername(String username);
	    
	   
}