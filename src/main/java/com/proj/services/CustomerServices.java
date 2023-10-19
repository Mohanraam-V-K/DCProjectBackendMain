package com.proj.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.model.Customer;
import com.proj.repository.CustomerRepo;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepo cusRepo;
    @Autowired
    private EmailSender mailSender;

    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok().body(cusRepo.findAll());
    }

    public ResponseEntity<String> create(Customer customer_data) {

        if (customer_data.getCustomerName() != null && customer_data.getEmail() != null) {

            Customer chkCustomer = cusRepo.findByEmail(customer_data.getEmail());

            if (chkCustomer == null) {
                System.out.println(customer_data.toString());
                customer_data.setStatus("active");
                cusRepo.save(customer_data);
                String body = "Account Created Successfully\r\n" + //
                        "-----------------------------------------\r\n" + //
                        "\r\n" + //
                        "username : " + customer_data.getEmail() + "\r\n" + //
                        "password : " + customer_data.getPassword() + "\r\n" + //
                        "\r\n" + //
                        "-----------------------------------------\r\n" + //
                        "\r\n" + //
                        "To access account visit our website";
                mailSender.sendMail(customer_data.getEmail(), "Account Created : DC Billing", body);
                return ResponseEntity.created(null).body("Created");

            } else {
                return ResponseEntity.ok().body("Already Exist");
            }

        } else {
            return ResponseEntity.badRequest().body("No values");
        }
    }

    public ResponseEntity<Customer> getUser(Customer customer_data){
        if (customer_data.getPassword() != null && customer_data.getEmail() != null) {

            Customer filteredData = cusRepo.findByEmailAndPassword(
                    customer_data.getEmail(),
                    customer_data.getPassword());
                    
            return ResponseEntity.status(HttpStatus.OK).body(filteredData);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    
    
    
    public ResponseEntity<String> changePassword(Customer data){
        if(data.getEmail() !=null && data.getPassword() != null){

            Customer selectedCustomer = cusRepo.findByEmail(data.getEmail());

            if(selectedCustomer !=null){
                selectedCustomer.setPassword(data.getPassword());

                String body = "Alert password Change\r\n" + //
                        "-----------------------------------------\r\n" + //
                        "\r\n" + //
                        "Your password changed Successfully."+
                        "\r\n" + //
                        "-----------------------------------------\r\n" + //
                        "\r\n" + //
                        "To access account visit http://localhost:3000";

                mailSender.sendMail(selectedCustomer.getEmail(), "Password changed : DC Billing", body);
                cusRepo.save(selectedCustomer);
                return ResponseEntity.ok().body("Success");
            }
            return ResponseEntity.badRequest().body("No user found");
        }else{
            return ResponseEntity.badRequest().body("Error");
        }
    }

    public ResponseEntity<String> forgotPass(Customer data){
        if(data.getEmail()  != null){

            Customer selectedCustomer = cusRepo.findByEmail(data.getEmail());

            if(selectedCustomer !=null){
                selectedCustomer.setPassword(data.getPassword());

                Random random = new Random();
                int min = 10000000; 
                int max = 99999999; 
                int randomNumber = random.nextInt(max - min + 1) + min;

                selectedCustomer.setPassword(Integer.toString(randomNumber));
                cusRepo.save(selectedCustomer);

                String body = "Alert Password Change request\r\n" + //
                        "-----------------------------------------\r\n" + //
                        "\r\n" + //
                        "Your Temp password : "+ selectedCustomer.getPassword() +
                        "\r\n" + //
                        "-----------------------------------------\r\n" + //
                        "\r\n" + //
                        "To access account visit http://localhost:4200";

                mailSender.sendMail(selectedCustomer.getEmail(), "Password change request : DC Billing 😉", body);
                return ResponseEntity.ok().body("Success");
            }
            return ResponseEntity.ok().body("No user found");

        }else{
            return ResponseEntity.badRequest().body("Failed");
        }
        
    }

	public ResponseEntity<String> updateProfile(Customer data) {
		if(data!=null) {
		Customer selectedCustomer = cusRepo.findByEmail(data.getEmail());
		if(selectedCustomer!=null) {
			if(data.getCustomerName()!=null&&data.getAddress()==null) {
				selectedCustomer.setCustomerName(data.getCustomerName());
				cusRepo.save(selectedCustomer);
				return ResponseEntity.ok().body("NameSuccess");
			}
			else if(data.getAddress()!=null&&data.getCustomerName()==null) {
				selectedCustomer.setAddress(data.getAddress());
				cusRepo.save(selectedCustomer);
				return ResponseEntity.ok().body("AddressSuccess");
			}
			else if(data.getAddress()!=null&&data.getCustomerName()!=null) {
				selectedCustomer.setCustomerName(data.getCustomerName());
				selectedCustomer.setAddress(data.getAddress());
				cusRepo.save(selectedCustomer);
				return ResponseEntity.ok().body("BothSuccess");
			}
		}
		else {
			return ResponseEntity.ok().body("No customer found");
			}
		}
		else {
			return ResponseEntity.badRequest().body("Failed");
		}
		return null;
	}

	
	

}