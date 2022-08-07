package com.sanchitacodes.InvoiceManagement.service;

import com.sanchitacodes.InvoiceManagement.entity.InvoiceDetail;
import com.sanchitacodes.InvoiceManagement.repository.InvoiceManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

public class SchedulerService {

    @Autowired
    private InvoiceManagementRepository invoiceManagementRepository;

    /*
    Scheduled for Daily invoice subscriptions
     */
    @Scheduled(fixedRate = 1000)
    public void scheduledFixedRateDailyTask(){
        invoiceGenerationDailySendEmail();
    }

    /*
    Scheduled for Monthly invoice subscriptions, the scheduler will run on
    25th of every month at 08:00 am.
     */
    @Scheduled(cron = "0 8 25 * *")
    public void scheduledFixedRateMonthlyTask(){
        invoiceGenerationMonthlySendEmail();
    }

    public String invoiceGenerationDailySendEmail() {
        List<InvoiceDetail> invoiceDetail = invoiceManagementRepository
                .findAll()
                .stream()
                .filter(record-> record.getBilling_interval()==1)
                .collect(Collectors.toList());
        createInvoiceAndSendEmail();
        return "Email sent with invoice!";
    }

    private String invoiceGenerationMonthlySendEmail() {
        List<InvoiceDetail> invoiceDetail = invoiceManagementRepository
                .findAll()
                .stream()
                .filter(record-> record.getBilling_interval()==2)
                .collect(Collectors.toList());
        createInvoiceAndSendEmail();
        return "Email sent with invoice!";
    }

    public void createInvoiceAndSendEmail() {
    }
}
