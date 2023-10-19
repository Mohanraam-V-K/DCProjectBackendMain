package com.proj.payload;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.proj.model.CustomerBill;

import lombok.ToString;
@ToString
public class ScheduleEmailReq {
    private String email;

    private String subject;

    private String body;

    private LocalDateTime dateTime;
    
    private CustomerBill data;

    private ZoneId timeZone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }

	public CustomerBill getData() {
		return data;
	}

	public void setData(CustomerBill data) {
		this.data = data;
	}
}