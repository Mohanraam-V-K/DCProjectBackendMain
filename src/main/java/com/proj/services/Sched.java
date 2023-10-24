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

    @Scheduled(cron = "0 0 9 * * *") // 9 am everyday
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
                emailData.setSubject("Your Plan is going to end today");
                emailData.setBody("<h3> Your plan end's today for "+customerBill.getCustomerphone()+". Please recharge immediately "
                		+ "for uninterrupted services. To recharge please visit our website. </h3>");
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
                customerBill.setPlanstatus("plan expired");
                cusBillrepo.save(customerBill);
                history.setBillId(customerBill.getBillId());
                history.setCustomerphone(customerBill.getCustomerphone());
                hisrepo.save(history);
//                cusBillrepo.delete(customerBill);
                System.out.println("same");
            } else if (daysDifference==1||daysDifference==2) {
                emailData.setSubject("Your Plan has expired");
                emailData.setBody("<h3> Your plan expired " + daysDifference + " days before for the number "+customerBill.getCustomerphone()+" ."
                		+ " Please visit our website and recharge immediately to avoid inactivity "
                		+ "and resume services</h3>");
                emailsScheduler.scheduleEmail(emailData,url);            
                System.out.println("past");
            } else {

                if (daysDifference == -1 || daysDifference == -2) {
                    emailData.setSubject("Your Plan is going to expire");
                    emailData.setBody("<h3> Your Plan will expire in "+-(daysDifference)+" days for the number "+customerBill.getCustomerphone()+" ."
                    		+ "This is just a reminder for you to recharge after "+-(daysDifference)+ "days</h3>");
                    emailsScheduler.scheduleEmail(emailData,url);
                }
                System.out.println(daysDifference);
                System.out.println("not");
            }

            if (daysDifference >= 10) {
            	emailData.setSubject("Your account has been suspended indefinitely");
            	emailData.setBody("<h3> Due to inactivity your account has been suspended"
            			+ " indefinitely.To purchase further plans for the number"+customerBill.getCustomerphone()+" in the future please visit our "
            			+ "website and raise an issue in report's section with proper reason for the"
            			+ " inactivity and if the customer service representative finds it appropriate and"
            			+ " doesn't conflict with our terms and conditions then your account will be "
            			+ "cleared for usage.");
                cus.setStatus("deactivated");
                cusRepo.save(cus);
                emailsScheduler.scheduleEmail(emailData,url);
                cusBillrepo.delete(customerBill);

            }
               


        }

    }

}