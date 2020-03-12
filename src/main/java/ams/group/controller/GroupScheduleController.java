package ams.group.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ams.group.domain.GroupScheduleVO;
import ams.group.service.GroupService;
import ams.server.service.CustomAuthorizationHandler;

@Controller
@RequestMapping("/group/**")
public class GroupScheduleController {

	@Inject GroupService service;
	@Inject CustomAuthorizationHandler customAuthorizationHandler;
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String groupSchedule(@RequestParam("groupId") int groupId, Model model, Principal principal) throws Exception {
		System.out.println("get group schedule");
		LocalDate today = LocalDate.now();
		
		LocalDate firstDate = today.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDate = today.with(TemporalAdjusters.lastDayOfMonth());
		
		int firstDay = firstDate.getDayOfWeek().getValue();
		int lastDay = lastDate.getDayOfWeek().getValue();
		
		LocalDate firstCal = firstDate.minusDays(firstDay);
		LocalDate lastCal = lastDate.plusDays(6-lastDay);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("groupId", groupId);
		map.put("startDate", firstCal);
		map.put("endDate", lastCal);
		
		List<GroupScheduleVO> scheduleList = service.getScheduleList(map);
		
		boolean isAdmin = customAuthorizationHandler.isAdmin(groupId, principal.getName());
		
		model.addAttribute("groupId", groupId);
		model.addAttribute("schedules", scheduleList);
		model.addAttribute("isAdmin", isAdmin);
		
		return "group_schedule";
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#info.get('groupId').asInt(), principal.username)")
	@ResponseBody
	@RequestMapping(value="/schedule", method=RequestMethod.POST)
	public int createSchedule(@RequestBody JsonNode info) throws Exception {
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
	@RequestMapping(value="/schedule", method=RequestMethod.PUT)
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
	@RequestMapping(value="/schedule", method=RequestMethod.DELETE)
	public int deleteSchedule(@RequestBody JsonNode info) throws Exception {
		return service.deleteSchedule(info.get("scheduleId").asInt());
	}
	
	@ResponseBody
	@RequestMapping(value="/calendar/{groupId}",  produces = "application/json; charset=utf8", method=RequestMethod.POST)
	public String getCalendar(@PathVariable("groupId") int groupId, @RequestBody JsonNode info) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> retObj = new HashMap<String, Object>();
		
		int year = info.get("year").asInt();
		int month = info.get("month").asInt();
		int term = info.get("term").asInt();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate today = LocalDate.of(year, month, 1).plusMonths(term);
		
		LocalDate firstDate = today.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDate = today.with(TemporalAdjusters.lastDayOfMonth());
		
		int firstDay = firstDate.getDayOfWeek().getValue() % 7;
		int lastDay = lastDate.getDayOfWeek().getValue() % 7;
		
		LocalDate firstCal = firstDate.minusDays(firstDay);
		LocalDate lastCal = lastDate.plusDays(6-lastDay);
		
		long weeks = ChronoUnit.WEEKS.between(firstCal, lastCal.plusDays(1));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("groupId", groupId);
		map.put("startDate", firstCal);
		map.put("endDate", lastCal);
		
		List<GroupScheduleVO> scheduleList = service.getScheduleList(map);
		
		Map<String, List<GroupScheduleVO>> calendar = new HashMap<String, List<GroupScheduleVO>>();
		
		LocalDate curDate = firstCal;
		while(curDate.compareTo(lastCal) <= 0) {
			String date = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
		retObj.put("weeks", weeks);
		retObj.put("year", today.getYear());
		retObj.put("month", today.getMonthValue());
		
		return mapper.writeValueAsString(retObj);
	}
}
