package com.sanchitacodes.InvoiceManagement;

import com.sanchitacodes.InvoiceManagement.service.SchedulerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InvoiceManagementApplication {
	public static void main(String[] args) {

		SpringApplication.run(InvoiceManagementApplication.class, args);
		SpringApplication.run(SchedulerService.class, args);
	}

}
