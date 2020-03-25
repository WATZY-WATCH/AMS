package ams.group.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ams.group.domain.GroupAttendanceVO;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupScheduleVO;
import ams.group.service.GroupService;

@Controller
public class GroupAttendanceController {
	
	@Inject GroupService service;
	
	@PreAuthorize("@customAuthorizationHandler.isMember(#groupId, principal.username)")
	@RequestMapping(path="/attend", method=RequestMethod.GET)
	public String getAttendance(@RequestParam("groupId") int groupId, @RequestParam("scheduleId") int scheduleId, Principal principal, Model model) throws Exception {
		System.out.println("get attendance page...");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		Date now = new Date();
		String userId = principal.getName();
		
		GroupScheduleVO vo = new GroupScheduleVO();
		vo.setGroupId(groupId);
		vo.setScheduleId(scheduleId);
		GroupScheduleVO schedule = service.getSchedule(vo);
		
		if(schedule == null) {
			return "error";
		}
		
		Date start = schedule.getBeginTime();
		Date end = schedule.getEndTime();
		
		GroupAttendanceVO ga = new GroupAttendanceVO();
		ga.setGroupId(groupId);
		ga.setScheduleId(scheduleId);
		ga.setUserId(principal.getName());
		
		long isTimeOn = (now.getTime() - start.getTime()) / 60000L;
		boolean finished = (now.getTime() > end.getTime());
		String attendanceStr = service.chkAttendanceStatus(ga);
		
		model.addAttribute("schedule", schedule);
		model.addAttribute("userId", userId);
		model.addAttribute("attendanceStatus", attendanceStr);
		model.addAttribute("isTimeOn", isTimeOn);
		model.addAttribute("finished", finished);
		model.addAttribute("start", format.format(start));
		model.addAttribute("end", format.format(end));
		
		return "group_attend";
	}
	
	@PreAuthorize("@customAuthorizationHandler.isMember(#reqInfo.get('groupId').asInt(), principal.username)")
	@ResponseBody
	@RequestMapping(path="/attend",  produces = "application/json; charset=utf8", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> postAttendace(@RequestBody JsonNode reqInfo, Principal principal) throws Exception {
		ResponseEntity<Map<String,Object>> entity = null;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> retObj = new HashMap<String, Object>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		int groupId = reqInfo.get("groupId").asInt();
		int scheduleId = reqInfo.get("scheduleId").asInt();
		String inputId = reqInfo.get("userId").asText();
		String userId = principal.getName();
	
		if(userId == null || !userId.equals(inputId)) {
			new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
		GroupMemberVO member = new GroupMemberVO();
		member.setGroupId(groupId);
		member.setUserId(userId);
		
		GroupScheduleVO vo = new GroupScheduleVO();
		vo.setGroupId(groupId);
		vo.setScheduleId(scheduleId);
		GroupScheduleVO schedule = service.getSchedule(vo);
		
		if(schedule == null) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
		Date start = schedule.getBeginTime();
		Date end = schedule.getEndTime();
		Date now = format.parse(format.format(new Date()));
		
		boolean isTimeout = (now.getTime() - start.getTime()) / 60000L <= 10L;
		
		GroupAttendanceVO ga = new GroupAttendanceVO();
		ga.setGroupId(groupId);
		ga.setUserId(userId);
		ga.setScheduleId(scheduleId);
		ga.setAttendaceStatus(isTimeout ? "출석" : "지각");
		
		service.requestAttend(ga, member, isTimeout);	
		
		retObj.put("chkTime", format.format(new Date()));
		retObj.put("status", (isTimeout ? "출석" : "지각"));
		
		entity = new ResponseEntity<Map<String,Object>>(retObj, HttpStatus.OK);
		
		return entity;
	}
}
