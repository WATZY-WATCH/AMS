package ams.group.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

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

import ams.group.domain.GroupScheduleVO;
import ams.group.service.GroupService;

@Controller
@RequestMapping("/group/**")
public class GroupScheduleController {

	@Inject GroupService service;
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String manageSchedule(@RequestParam("groupId") int groupId, Model model) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate today = LocalDate.now();
		int day = today.getDayOfWeek().getValue();
		
		LocalDate firstDate = today.with(TemporalAdjusters.firstDayOfMonth()); //이번 달의 첫 날 
		LocalDate lastDate = today.with(TemporalAdjusters.lastDayOfMonth()); //이번 달의 마지막 날 
		
		int firstDay = firstDate.getDayOfWeek().getValue(); //첫 날의 요일 
		int lastDay = lastDate.getDayOfWeek().getValue(); //마지막 날의 요일 
		
		LocalDate firstCal = firstDate.minusDays(firstDay); //캘린더에 나타낼 첫 날 
		LocalDate lastCal = lastDate.plusDays(6-lastDay); //캘린더에 나타낼 마지막 날 
		
		long weeks = ChronoUnit.WEEKS.between(firstCal, lastCal.plusDays(1)); //캘린더에 나타나는 주(week)의 수 
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("groupId", groupId);
		map.put("startDate", firstCal);
		map.put("endDate", lastCal);
		
		List<GroupScheduleVO> scheduleList = service.getScheduleList(map);
		
		model.addAttribute("groupId", groupId);
		model.addAttribute("schedules", scheduleList);
		
		return "group_schedule";
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#info.get('groupId').asInt(), principal.username)")
	@ResponseBody
	@RequestMapping(value="/saveSchedule", method=RequestMethod.POST)
	public int saveLocationInfo(@RequestBody JsonNode info) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		
		GroupScheduleVO vo = new GroupScheduleVO();
		vo.setGroupId(info.get("groupId").asInt());
		vo.setPlaceLatitude(info.get("position").get("y").asDouble());
		vo.setPlaceLongitude(info.get("position").get("x").asDouble());
		vo.setPlaceAddress(info.get("address").asText());
		vo.setBeginTime(format.parse(info.get("beginTime").asText()));
		vo.setEndTime(format.parse(info.get("endTime").asText()));
		vo.setBuildingName(info.get("building_name").asText());
		
		return service.createSchedule(vo);
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#info.get('groupId').asInt(), principal.username)")
	@ResponseBody
	@RequestMapping(value="/modifySchedule", method=RequestMethod.POST)
	public int modifySchedule(@RequestBody JsonNode info) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
		
		GroupScheduleVO vo = new GroupScheduleVO();
		vo.setGroupId(info.get("groupId").asInt());
		vo.setScheduleId(info.get("scheduleId").asInt());
		vo.setPlaceLatitude(info.get("position").get("y").asDouble());
		vo.setPlaceLongitude(info.get("position").get("x").asDouble());
		vo.setPlaceAddress(info.get("address").asText());
		vo.setBeginTime(format.parse(info.get("beginTime").asText()));
		vo.setEndTime(format.parse(info.get("endTime").asText()));
		vo.setBuildingName(info.get("building_name").asText());
		
		return service.modifySchedule(vo);
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#info.get('groupId').asInt(), principal.username)")
	@ResponseBody
	@RequestMapping(value="/deleteSchedule", method=RequestMethod.POST)
	public int deleteSchedule(@RequestBody JsonNode info) throws Exception {
		return service.deleteSchedule(info.get("scheduleId").asInt());
	}
	
	@ResponseBody
	@RequestMapping(value="/getCalendar", method=RequestMethod.POST)
	public String getCalendar(@RequestBody JsonNode info) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> retObj = new HashMap<String, Object>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		LocalDate today = LocalDate.now();
		int day = today.getDayOfWeek().getValue();
		
		LocalDate firstDate = today.with(TemporalAdjusters.firstDayOfMonth()); //이번 달의 첫 날 
		LocalDate lastDate = today.with(TemporalAdjusters.lastDayOfMonth()); //이번 달의 마지막 날 
		
		int firstDay = firstDate.getDayOfWeek().getValue(); //첫 날의 요일 
		int lastDay = lastDate.getDayOfWeek().getValue(); //마지막 날의 요일 
		
		LocalDate firstCal = firstDate.minusDays(firstDay); //캘린더에 나타낼 첫 날 
		LocalDate lastCal = lastDate.plusDays(6-lastDay); //캘린더에 나타낼 마지막 날 
		
		long weeks = ChronoUnit.WEEKS.between(firstCal, lastCal.plusDays(1)); //캘린더에 나타나는 주(week)의 수 
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("groupId", info.get("groupId").asInt());
		map.put("startDate", firstCal);
		map.put("endDate", lastCal);
		
		List<GroupScheduleVO> scheduleList = service.getScheduleList(map);
		
		Map<String, List<GroupScheduleVO>> calendar = new HashMap<String, List<GroupScheduleVO>>();
		
		LocalDate curDate = firstCal;
		while(curDate.compareTo(lastCal) <= 0) {
			String date = curDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			List<GroupScheduleVO> vo = new ArrayList<>();
			calendar.put(date, vo);
			curDate = curDate.plusDays(1);
		}
		
		for(GroupScheduleVO s : scheduleList) {
			String date = format.format(s.getBeginTime());
			List<GroupScheduleVO> list = calendar.get(date);
			list.add(s);
			calendar.put(date, list);
		}
		
		retObj.put("calendar", calendar);
		retObj.put("firstDate", firstCal);
		retObj.put("lastDate", lastCal);
		
		return mapper.writeValueAsString(retObj);
	}
}
