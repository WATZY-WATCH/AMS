package ams.server.controller;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import ams.server.service.SchedulingService;

@Controller
public class SchedulingController {
	
	@Inject SchedulingService service;
	
	@Scheduled(cron="0 0/2 0/1 * * *")
	public void chkAbsent() throws Exception {
		System.out.println("Update Absent every hour....");
		
		service.updateAbsent();
		service.updateStudyGroup();
		
		return;
	}
}
