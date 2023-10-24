package com.proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proj.model.CustomerBill;
import com.proj.services.CustomerBillService;

@RestController
@RequestMapping("api/v1/bill")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerBillController {

    @Autowired
    private CustomerBillService customerBillService;

    @GetMapping()
    public ResponseEntity<List<CustomerBill>> getAll() {
        return customerBillService.getAll();
    }

    @PostMapping()
    public ResponseEntity<String> addBill(@RequestBody CustomerBill data){
        return customerBillService.create(data);
    }   
    
    @GetMapping("/{email}")
    public ResponseEntity<CustomerBill> viewBill(@PathVariable String email) {
    	return customerBillService.getBill(email);
    }
    
//    @DeleteMapping("/{email}")
//    public ResponseEntity<String> deleteprofile(@PathVariable String email){
//    	return customerBillService.deleteProfile(email);
//    }

}