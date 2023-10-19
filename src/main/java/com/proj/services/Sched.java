package com.proj.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.proj.model.BillHistory;
import com.proj.model.Customer;
import com.proj.model.CustomerBill;
import com.proj.payload.ScheduleEmailReq;
import com.proj.repository.BillHistoryRepo;
import com.proj.repository.CustomerBillRepo;
import com.proj.repository.CustomerRepo;

@Service
public class Sched {

    @Autowired
    private CustomerBillRepo cusBillrepo;
    @Autowired
    private CustomerRepo cusRepo;
    
    @Autowired
    private BillHistoryRepo hisrepo;

    private final EmailScheduler emailsScheduler;


    public Sched(EmailScheduler emailScheduler) {
        this.emailsScheduler = emailScheduler;
    }

    @Scheduled(cron = "0 0 10 * * *") // 12noon everyday
    public void sendEmail() {
        List<CustomerBill> allBills = cusBillrepo.findAll();
        
        String url = "http://localhost:8060/api/v1/emailSchedular/scheduleEmail/due";

        ScheduleEmailReq emailData = new ScheduleEmailReq();

        for (CustomerBill customerBill : allBills) {
            LocalDateTime date = LocalDateTime.parse(customerBill.getPlanDueDate(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDate dueDate = date.toLocalDate();
            LocalDate curDate = currentDate.toLocalDate();

            Customer cus = cusRepo.findByCustomerId(UUID.fromString(customerBill.getCustomerId()));
            emailData.setEmail(cus.getEmail());
            emailData.setTimeZone(ZoneId.of("Asia/Kolkata"));
            emailData.setDateTime(LocalDateTime.now().plusSeconds(3));

            long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

            if (dueDate.isEqual(curDate)) {
                emailData.setSubject("Your Plan is Going to end today");
                emailData.setBody("<h1> PLan is end today </h1>");
                emailsScheduler.scheduleEmail(emailData,url);
                System.out.println(cus.toString());
                BillHistory history=new BillHistory();
                history.setCustomerId(customerBill.getCustomerId());
                history.setEmail(customerBill.getEmail());
                history.setPlan_type(customerBill.getPlan_type());
                history.setPlanAmount(customerBill.getPlanAmount());
                history.setPlanDueDate(customerBill.getPlanDueDate());
                history.setPlanDuration(customerBill.getPlanDuration());
                history.setPlanName(customerBill.getPlanName());
                history.setPlanstatus("plan expired");
                history.setBillId(customerBill.getBillId());
                hisrepo.save(history);
                cusBillrepo.delete(customerBill);
                System.out.println("same");
            } else if (date.isBefore(currentDate)) {
                emailData.setSubject("Your Plan is ended pls recharge");
                emailData.setBody("<h1> PLan ended " + daysDifference + " Days before</h1>");
                emailsScheduler.scheduleEmail(emailData,url);            
                System.out.println("past");
            } else {

                if (daysDifference == -1 || daysDifference == -2) {
                    emailData.setSubject("Your Plan is Going to end");
                    emailData.setBody("<h1> PLan is Going to end</h1>");
                    emailsScheduler.scheduleEmail(emailData,url);
                }

                System.out.println("not");
            }

            if (daysDifference == 10) {
                cus.setStatus("deactivated");
                cusRepo.save(cus);

//                cusBillrepo.delete(customerBill);

            }
               


        }

    }

}