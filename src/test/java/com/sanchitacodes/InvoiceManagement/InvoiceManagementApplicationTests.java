package com.sanchitacodes.InvoiceManagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootTest
class InvoiceManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testGenerateInvoice(){

	}

	@Test
	public void givenUsingTimer_whenSchedulingDailyTask_thenCorrect() {
		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				System.out.println("Task performed on " + new Date());
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1000L;
		long period = 1000L * 60L * 60L * 24L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}

}
