package com.proj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail( String toEmail,String Subject, String Body ){
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("DC Billing  <testserverpy0@gmail.com>");
            message.setTo(toEmail);
            message.setSubject(Subject);
            message.setText(Body);

            mailSender.send(message);
            System.out.println("Success ...");
    }

}