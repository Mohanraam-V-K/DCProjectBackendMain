package com.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.proj.model.Report;
import com.proj.repository.ReportRepo;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepo reportrepo;
	
	public ResponseEntity<String> create(Report data) {
		if(data!=null) {
			reportrepo.save(data);
			return ResponseEntity.ok().body("Success");
		}
		else {
			return ResponseEntity.badRequest().body("bad boy");
		}
	}
	
	public ResponseEntity<List<Report>> getAll() {
        return ResponseEntity.ok().body(reportrepo.findAll());
    }

}