package com.proj.services;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proj.payload.ScheduleEmailReq;

@Service
public class EmailScheduler {
    private final RestTemplate restTemplate;

    public EmailScheduler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void scheduleEmail(ScheduleEmailReq emailRequest , String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ScheduleEmailReq> requestEntity = new HttpEntity<>(emailRequest, headers);

        restTemplate.postForLocation(url, requestEntity);
    }

	
}