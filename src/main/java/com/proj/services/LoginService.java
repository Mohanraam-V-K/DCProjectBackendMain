package com.proj.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.model.Login;
import com.proj.repository.LoginRepo;

@Service
public class LoginService {
    // REPO
	 @Autowired
	    private LoginRepo loginRepo;

	    public ResponseEntity<String> create(Login login_data) {
	        if (login_data.getUsername() != null && login_data.getPassword() != null) {
	            Login chkData = loginRepo.findByUsername(login_data.getUsername());

	            if(chkData ==null){
	                loginRepo.save(login_data);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
	            }else{
	                return ResponseEntity.status(HttpStatus.CONFLICT).body("You are a Failure ULTRA MAX PRO");
	            }

	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are a Failure");
	        }
	    }

	    public ResponseEntity<List<Login>> getAll() {
	        
	        if (loginRepo.findAll() != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(loginRepo.findAll());
	        } else {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        }
	    }

	    public ResponseEntity<Login> getUser(Login login_data){
	        if (login_data.getPassword() != null && login_data.getUsername() != null) {

	            Login filteredData = loginRepo.findByUsernameAndPassword(
	                    login_data.getUsername(),
	                    login_data.getPassword());
	                    
	            return ResponseEntity.status(HttpStatus.OK).body(filteredData);

	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	    public ResponseEntity<String> edit(Login login_data , UUID id) {
	        if (login_data.getUsername() != null && login_data.getPassword() != null) {
	            
	            Login filteredData = loginRepo.findByUserId(id);

	            filteredData.setUsername(login_data.getUsername());
	            filteredData.setPassword(login_data.getPassword());

	            loginRepo.save(filteredData);

	            return ResponseEntity.status(HttpStatus.OK).body("Success");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("You are a Failure");
	        }
	    }

	    public ResponseEntity<String> delete( UUID id){
	        if (id != null) {
	            
	            Login filteredData = loginRepo.findByUserId(id);

	            loginRepo.delete(filteredData);

	            return ResponseEntity.status(HttpStatus.OK).body("Success");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("You are a Failure");
	        }
	    }
	   


}