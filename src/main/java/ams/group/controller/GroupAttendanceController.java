package ams.group.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
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
	
	@RequestMapping(path="/attend", method=RequestMethod.GET)
	public String getAttendance(@RequestParam("groupId") int groupId, @RequestParam("scheduleId") int scheduleId, Principal principal, Model model) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		Date now = new Date();
		String userId = principal.getName();
		
		GroupMemberVO member = new GroupMemberVO();
		member.setGroupId(groupId);
		member.setUserId(userId);
		int isMember = service.memberChk(member);
		
		GroupScheduleVO vo = new GroupScheduleVO();
		vo.setGroupId(groupId);
		vo.setScheduleId(scheduleId);
		GroupScheduleVO schedule = service.getSchedule(vo);
		
		if(isMember == 0 || schedule == null) {
			return "error";
		}
		
		Date start = schedule.getBeginTime();
		Date end = schedule.getEndTime();
		
		GroupAttendanceVO ga = new GroupAttendanceVO();
		ga.setGroupId(groupId);
		ga.setScheduleId(scheduleId);
		ga.setUserId(principal.getName());
		
		boolean isTimeOn = Math.abs((now.getTime() - start.getTime())) / 60000L <= 10L;
		boolean finished = (now.getTime() > end.getTime());
		String attendanceStr = service.chkAttendanceStatus(ga);
		
		model.addAttribute("userId", userId);
		model.addAttribute("groupId", groupId);
		model.addAttribute("scheduleId", schedule.getScheduleId());
		model.addAttribute("attendanceStatus", attendanceStr);
		model.addAttribute("isTimeOn", isTimeOn);
		model.addAttribute("finished", finished);
		model.addAttribute("start", format.format(start));
		return "group_attend";
	}
	
	@ResponseBody
	@RequestMapping(path="/attend",  produces = "application/json; charset=utf8", method=RequestMethod.POST)
	public String postAttendace(@RequestBody JsonNode reqInfo, Principal principal) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> retObj = new HashMap<String, Object>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		int groupId = reqInfo.get("groupId").asInt();
		int scheduleId = reqInfo.get("scheduleId").asInt();
		String inputId = reqInfo.get("userId").asText();
		String userId = principal.getName();
		
		if(!userId.equals(inputId)) {
			retObj.put("error", "잘못된 요청입니다. ");
			return mapper.writeValueAsString(retObj);
		}
		
		GroupMemberVO member = new GroupMemberVO();
		member.setGroupId(groupId);
		member.setUserId(userId);
		int isMember = service.memberChk(member);
		
		GroupScheduleVO vo = new GroupScheduleVO();
		vo.setGroupId(groupId);
		vo.setScheduleId(scheduleId);
		GroupScheduleVO schedule = service.getSchedule(vo);
		
		if(isMember == 0 || schedule == null) {
			retObj.put("error", "잘못된 요청입니다. ");
			return mapper.writeValueAsString(retObj);
		}
		
		Date start = schedule.getBeginTime();
		Date end = schedule.getEndTime();
		Date now = format.parse(format.format(new Date()));
		
		boolean isTimeout = (now.getTime() - start.getTime()) / 60000L <= 10L; //출석 시간 이내 접근인지 확인 
		
		GroupAttendanceVO ga = new GroupAttendanceVO();
		ga.setGroupId(groupId);
		ga.setUserId(userId);
		ga.setScheduleId(scheduleId);
		ga.setAttendaceStatus(isTimeout ? "출석" : "지각");
		
		System.out.println(isTimeout + " " + ga.getAttendaceStatus());
		
		int ret = service.requestAttend(ga);
		
		if(ret == 0) {
			retObj.put("error", "에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
			return mapper.writeValueAsString(retObj);
		}
		
		retObj.put("chkTime", format.format(new Date()));
		retObj.put("status", (isTimeout ? "출석" : "지각"));
		
		return mapper.writeValueAsString(retObj);
	}
}
