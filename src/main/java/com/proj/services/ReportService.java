package com.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.model.Customer;
import com.proj.model.Report;
import com.proj.repository.ReportRepo;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepo reportrepo;
	
	@Autowired
    private EmailSender mailSender;
	
	public ResponseEntity<String> create(Report data) {
		if(data!=null) {
			data.setStatus("pending");
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

	public ResponseEntity<Report> getreport(String email) {
		if (email != null) {
        	Report selectedBill=reportrepo.findBycustomeremail(email);
        	return ResponseEntity.ok().body(selectedBill);

        } else {
            return ResponseEntity.badRequest().body(null);
        }
	}
	
	public ResponseEntity<String> reportyes(String email) {
		if(email!=null) {
			Report selected=reportrepo.findBycustomeremail(email);
			selected.setStatus("completed");
			reportrepo.save(selected);
			String body = "Your issue has been taken into consideration will let you know once the issue has been resolved";
            mailSender.sendMail(email, "Issue accepted : DC Billing", body);
            return ResponseEntity.ok().body("Accepted");
		}	
		else {
			return ResponseEntity.badRequest().body("No values");
		}
	}
	
	public ResponseEntity<String> reportno(String email) {
		if(email!=null) {
			Report selected=reportrepo.findBycustomeremail(email);
			selected.setStatus("completed");
			reportrepo.save(selected);
			String body = "Your issue does not coinside with our terms and conditions";
            mailSender.sendMail(email, "Issue rejected : DC Billing", body);
            return ResponseEntity.ok().body("Rejected");
		}	
		else {
			return ResponseEntity.badRequest().body("No values");
		}
	}

}