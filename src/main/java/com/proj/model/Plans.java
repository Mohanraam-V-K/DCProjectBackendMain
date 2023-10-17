package com.proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Plans {
	 @Id
	    private String planId;

	    private String planType;
	    private String planName;
		private Double planAmount;
	    public Plans(String planId, String planType, String planName, Double planAmount, int planValidity, String data,
				String talktime, String sms) {
			super();
			this.planId = planId;
			this.planType = planType;
			this.planName = planName;
			this.planAmount = planAmount;
			this.planValidity = planValidity;
			this.data = data;
			this.talktime = talktime;
			this.sms = sms;
		}
		public Plans() {
			super();
			// TODO Auto-generated constructor stub
		}
		private int planValidity;
	    private String data;
	    private String talktime;
	    private String sms;
		public String getPlanId() {
			return planId;
		}
		public void setPlanId(String planId) {
			this.planId = planId;
		}
		public String getPlanType() {
			return planType;
		}
		public void setPlanType(String planType) {
			this.planType = planType;
		}
		public String getPlanName() {
			return planName;
		}
		public void setPlanName(String planName) {
			this.planName = planName;
		}
		public Double getPlanAmount() {
			return planAmount;
		}
		public void setPlanAmount(Double planAmount) {
			this.planAmount = planAmount;
		}
		public int getPlanValidity() {
			return planValidity;
		}
		public void setPlanValidity(int planValidity) {
			this.planValidity = planValidity;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public String getTalktime() {
			return talktime;
		}
		public void setTalktime(String talktime) {
			this.talktime = talktime;
		}
		public String getSms() {
			return sms;
		}
		public void setSms(String sms) {
			this.sms = sms;
		}
	    
	    
}
