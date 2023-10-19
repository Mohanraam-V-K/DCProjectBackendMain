package com.proj.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.payload.*;
import com.proj.model.Customer;
import com.proj.model.CustomerBill;
import com.proj.repository.CustomerBillRepo;
import com.proj.repository.CustomerRepo;

@Service
public class CustomerBillService {

    @Autowired
    private CustomerBillRepo cusBillRepo;

    private final EmailScheduler emailsScheduler;
    
    
    public CustomerBillService(EmailScheduler emailScheduler) {
        this.emailsScheduler = emailScheduler;
    }

    @Autowired
    private CustomerRepo cusRepo;

    public ResponseEntity<List<CustomerBill>> getAll() {
        return ResponseEntity.ok().body(cusBillRepo.findAll());
    }

    public ResponseEntity<String> create(CustomerBill data) {
    	if (data != null) {
            ScheduleEmailReq emailData = new ScheduleEmailReq();
            Customer cus = cusRepo.findByCustomerId(UUID.fromString(data.getCustomerId()));
            CustomerBill selectedCustomerbill=cusBillRepo.findByemail(cus.getEmail());
            if(selectedCustomerbill!=null) {
            	
            	cusBillRepo.delete(selectedCustomerbill);
            	System.out.println(selectedCustomerbill.getBillId());
            	//customer history la add pannanum data va
            }
            data.setPlanstatus("active plan");
            emailData.setEmail(cus.getEmail());
            emailData.setTimeZone(ZoneId.of("Asia/Kolkata"));
            emailData.setBody("plan is " + data.getPlanName()+" for the number "+data.getCustomerphone());
            emailData.setSubject("plan activated");
            emailData.setData(data);
            emailData.setDateTime(LocalDateTime.now().plusSeconds(3));
            emailsScheduler.scheduleEmail(emailData,"http://localhost:8060/api/v1/emailSchedular/scheduleEmail/create");
            //emailsScheduler.scheduleEmail(emailData);
            cusBillRepo.save(data);
            return ResponseEntity.ok().body("Success");
        } 
    	else {
            return ResponseEntity.badRequest().body("Fail");
        }
    }

    public ResponseEntity<CustomerBill> getBill(String email){
        if (email != null) {
        	CustomerBill selectedBill=cusBillRepo.findByemail(email);
        	return ResponseEntity.ok().body(selectedBill);

        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}