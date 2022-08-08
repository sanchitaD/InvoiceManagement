package com.sanchitacodes.InvoiceManagement.service;

import com.sanchitacodes.EmailSystem.EmailSenderService;
import com.sanchitacodes.InvoiceManagement.entity.InvoiceDetail;
import com.sanchitacodes.InvoiceManagement.repository.InvoiceManagementRepository;
import com.sanchitacodes.InvoiceManagement.utility.InvoiceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static com.sanchitacodes.InvoiceManagement.utility.InvoiceManagementConstants.MESSAGE;
import static com.sanchitacodes.InvoiceManagement.utility.InvoiceManagementConstants.SUBJECT;

@Slf4j
public class SchedulerService {

    @Autowired
    private InvoiceManagementRepository invoiceManagementRepository;
    @Autowired
    private InvoiceGenerator invoiceGenerator;
    @Autowired
    private EmailSenderService emailSenderService;

    /*
    Scheduled for Daily invoice subscriptions
     */
    @Scheduled(cron = "0 10 * * *")
    public void scheduledDailyTask() throws MessagingException {
        log.info("Inside scheduledFixedRateDailyTask of SchedulerService, creating invoice and sending daily email!");
        invoiceGenerationDailySendEmail();
    }

    /*
    Scheduled for Monthly invoice subscriptions, the scheduler will run on
    25th of every month at 08:00 am.
     */
    @Scheduled(cron = "0 8 25 * *")
    public void scheduledMonthlyTask() throws MessagingException {
        log.info("Inside scheduledFixedRateDailyTask of SchedulerService, creating invoice and sending monthly email!");
        invoiceGenerationMonthlySendEmail();
    }

    public String invoiceGenerationDailySendEmail() throws MessagingException {
        List<InvoiceDetail> invoiceDetail = invoiceManagementRepository
                .findAll()
                .stream()
                .filter(record-> record.getBilling_interval()==1)
                .collect(Collectors.toList());
        for(int i = 0; i<invoiceDetail.size(); i++) {
            File newInvoice = new File("src/main/resources/" + invoiceDetail.get(i).getOrderId() + "_invoice.pdf");
            invoiceGenerator.createInvoiceInPdf(newInvoice, invoiceDetail.get(i));
            emailSenderService.sendEmailWithAttachment(
                    invoiceDetail.get(i).getEmail(), MESSAGE, SUBJECT, newInvoice);
        }
        return "Email sent with invoice!";
    }

    private String invoiceGenerationMonthlySendEmail() throws MessagingException {

        List<InvoiceDetail> invoiceDetail = invoiceManagementRepository
                .findAll()
                .stream()
                .filter(record-> record.getBilling_interval()==2)
                .collect(Collectors.toList());
        for(int i = 0; i<invoiceDetail.size(); i++) {
            File newInvoice = new File("src/main/resources/" + invoiceDetail.get(i).getOrderId() + "_invoice.pdf");
            invoiceGenerator.createInvoiceInPdf(newInvoice, invoiceDetail.get(i));
            emailSenderService.sendEmailWithAttachment(
                    invoiceDetail.get(i).getEmail(), MESSAGE, SUBJECT, newInvoice);
        }
        return "Email sent with invoice!";
    }
}
