package com.proj.controller;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.model.Login;
import com.proj.services.LoginService;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
public class LoginController {

    // Service
	 @Autowired
	    private LoginService loginService;
	    
	    @PostMapping()
	    public ResponseEntity<String> createUser( @RequestBody Login login_details ){
	        return loginService.create(login_details);
	    }

	    @GetMapping()
	    public ResponseEntity<List<Login>> getUsers(){
	        return loginService.getAll();
	    }

	    @PostMapping("/user")
	    public ResponseEntity<Login> getUser(@RequestBody Login login_data){
	        return loginService.getUser(login_data);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<String> editUser(@PathVariable UUID id ,@RequestBody Login login_data){
	        return loginService.edit(login_data, id);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteUser(@PathVariable UUID id ){
	        return loginService.delete(id);
	    }
	    
	    

}
