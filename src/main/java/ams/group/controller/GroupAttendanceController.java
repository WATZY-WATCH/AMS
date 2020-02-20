package ams.group.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class GroupAttendanceController {
	
	private boolean attendanceStatus = false; //출석 체크 여부
	private boolean isTimeout = false;
	
	@RequestMapping(path="/attend", method=RequestMethod.GET)
	public String getAttendance(@RequestParam("groupId") int groupId, Principal principal, Model model) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		Date now = new Date();
		String userId = principal.getName();
		String baseTime = "2020-02-20 13:20";
		Date base = format.parse(baseTime);
		
		System.out.println("now: " + now);
		System.out.println("base: " + base);
		
		isTimeout = (now.getTime() <= base.getTime()) || (now.getTime() - base.getTime()) / 60000L <= 10L; //출석 시간 이내 접근인지 확- 
		
		System.out.println(attendanceStatus + " " + isTimeout);
		model.addAttribute("userId", userId);
		model.addAttribute("groupId", groupId);
		model.addAttribute("attendanceStatus", attendanceStatus);
		model.addAttribute("isTimeout", isTimeout);
		model.addAttribute("base", baseTime);
		return "group_attend";
	}
	
	@ResponseBody
	@RequestMapping(path="/attend",  produces = "application/json; charset=utf8", method=RequestMethod.POST)
	public String postAttendace(@RequestBody JsonNode reqInfo, Principal principal) throws ParseException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		String nowTime = reqInfo.get("date").asText();
		String inputId = reqInfo.get("userId").asText();
		String userId = principal.getName();
		String baseTime = "2020-02-20 13:20";
		Date base = format.parse(baseTime);
		Date now = format.parse(nowTime);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> retObj = new HashMap<String, Object>();
		if(inputId == userId) {
			System.out.println(inputId + ": :" + userId + ".");
			retObj.put("error", "잘못된 요청입니다. ");
			return mapper.writeValueAsString(retObj);
		}
		
		isTimeout = (now.getTime() <= base.getTime()) || (now.getTime() - base.getTime()) / 60000L <= 10L; //출석 시간 이내 접근인지 확- 
		if(isTimeout) attendanceStatus = true;
		
		
		retObj.put("status", (isTimeout ? "출석완료" : "지각"));
		
		return mapper.writeValueAsString(retObj);
	}
}
