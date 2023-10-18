package com.proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.model.BillHistory;
import com.proj.model.Report;
import com.proj.services.ReportService;

@RestController
@RequestMapping("api/v1/report")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

	@Autowired
	private ReportService reportservice;
	
	@GetMapping()
    public ResponseEntity<List<Report>> getAllReports() {
        return reportservice.getAll();
    }
	
	@PostMapping()
    public ResponseEntity<String> addReport(@RequestBody Report data){
        return reportservice.create(data);
    }
	
	@GetMapping("/{email}")
    public ResponseEntity<List<Report>> viewBill(@PathVariable String email) {
    	return reportservice.getreport(email);
    }

}
