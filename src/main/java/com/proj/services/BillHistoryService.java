package com.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.model.BillHistory;
import com.proj.model.CustomerBill;
import com.proj.repository.BillHistoryRepo;

@Service
public class BillHistoryService {
	
	@Autowired
    private BillHistoryRepo billHistoryRepo;

	public ResponseEntity<List<BillHistory>> getAll() {
		return ResponseEntity.ok().body(billHistoryRepo.findAll());
	}
	
	public ResponseEntity<List<BillHistory>> getBill(String email){
        if (email != null) {
        	List<BillHistory> selectedBill=billHistoryRepo.findByemail(email);
        	return ResponseEntity.ok().body(selectedBill);

        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
	
}
