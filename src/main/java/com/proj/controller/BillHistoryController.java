package com.proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.model.BillHistory;
import com.proj.model.CustomerBill;
import com.proj.services.BillHistoryService;

@RestController
@RequestMapping("api/v1/history")
@CrossOrigin(origins = "http://localhost:4200")
public class BillHistoryController {
	
	@Autowired
	private BillHistoryService billHistoryService;
	
	@GetMapping()
    public ResponseEntity<List<BillHistory>> getAll() {
        return billHistoryService.getAll();
    }
	
	@GetMapping("/{email}")
    public ResponseEntity<List<BillHistory>> viewBill(@PathVariable String email) {
    	return billHistoryService.getBill(email);
    }
}
