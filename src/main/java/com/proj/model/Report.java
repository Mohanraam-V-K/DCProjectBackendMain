package com.proj.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Report {
	
	@Id
	@SequenceGenerator(
			name="Role_sequence",
			sequenceName="Role_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "Role_sequence"
	)
	private int reportid;
	
	private String customeremail;
	private String category;
	private String description;
	private String date;
	public int getReportid() {
		return reportid;
	}
	public void setReportid(int reportid) {
		this.reportid = reportid;
	}
	public String getCustomeremail() {
		return customeremail;
	}
	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Report(int reportid, String customeremail, String category, String description, String date) {
		super();
		this.reportid = reportid;
		this.customeremail = customeremail;
		this.category = category;
		this.description = description;
		this.date = date;
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
