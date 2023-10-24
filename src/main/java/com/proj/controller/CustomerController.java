package com.proj.controller;
import java.util.List;

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

import com.proj.model.Customer;
import com.proj.services.CustomerServices;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    private CustomerServices cusService;

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return cusService.getAll();
    }

    @PostMapping()
    public ResponseEntity<String> addCustomer(@RequestBody Customer data) {
        return cusService.create(data);
    }
    
    @PostMapping("/user")
    public ResponseEntity<Customer> viewCustomer(@RequestBody Customer data) {
    	return cusService.getUser(data);
    }
    @PutMapping("/changepass")
    public ResponseEntity<String> changePassword(@RequestBody Customer data) {
        return cusService.changePassword(data);
    }

    @PostMapping("/forgotPassGen")
    public ResponseEntity<String> forgotPass(@RequestBody Customer data) {
        return cusService.forgotPass(data);
    }
    
    @PutMapping("/updateprofile")
    public ResponseEntity<String> updateProfile(@RequestBody Customer data) {
        return cusService.updateProfile(data);
    }
    
//    @DeleteMapping("/{email}")
//    public ResponseEntity<String> deleteprofile(@PathVariable String email){
//    	return cusService.deleteProfile(email);
//    }
}