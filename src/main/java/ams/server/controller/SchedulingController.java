package ams.server.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import ams.group.domain.GroupAttendanceVO;
import ams.server.service.SchedulingService;

@Controller
public class SchedulingController {
	
	@Inject SchedulingService service;
	
	@Scheduled(cron="0 0 0/1 * * *")
	public void chkAbsent() throws Exception {
		System.out.println("매 시간마다 결석 체크....");
		
		List<GroupAttendanceVO> list = service.getAbsentList();
		
		for(GroupAttendanceVO l : list) {
			System.out.println(l.getGroupId() + " " + l.getScheduleId() + " " + l.getUserId() + " " + l.getAttendaceStatus());
		}
		
		int ret = service.insertAbsentList(list);
		
		System.out.println("ret: " + ret);
		
		return;
	}
}
