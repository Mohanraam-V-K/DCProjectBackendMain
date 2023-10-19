package com.proj.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BillHistory {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID billId;
	
    private String customerId;
    
    private String customerphone;
    
	public String getCustomerphone() {
		return customerphone;
	}
	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}
	public UUID getBillId() {
		return billId;
	}
	public void setBillId(UUID billId) {
		this.billId = billId;
	}
	private String email;
    public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPlan_type() {
		return plan_type;
	}
	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}
	public Double getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(String planDuration) {
		this.planDuration = planDuration;
	}
	public String getPlanDueDate() {
		return planDueDate;
	}
	public void setPlanDueDate(String planDueDate) {
		this.planDueDate = planDueDate;
	}
	private String plan_type;
    public BillHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BillHistory(UUID billId, String customerId, String email, String plan_type, Double planAmount,
			String planName, String planDuration, String planDueDate, String planstatus , String customerphone) {
		super();
		this.billId = billId;
		this.customerId = customerId;
		this.email = email;
		this.plan_type = plan_type;
		this.planAmount = planAmount;
		this.planName = planName;
		this.planDuration = planDuration;
		this.planDueDate = planDueDate;
		this.planstatus = planstatus;
		this.customerphone=customerphone;
	}
	private Double planAmount;
    private String planName;
    private String planDuration;
    private String planDueDate;
    private String planstatus;
	public String getPlanstatus() {
		return planstatus;
	}
	public void setPlanstatus(String planstatus) {
		this.planstatus = planstatus;
	}
}
