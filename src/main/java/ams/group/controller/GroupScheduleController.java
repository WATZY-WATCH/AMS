package ams.group.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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

import ams.group.domain.GroupScheduleVO;
import ams.group.service.GroupService;

@Controller
@RequestMapping("/group/**")
public class GroupScheduleController {

	@Inject GroupService service;
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String manageSchedule(@RequestParam("groupId") int groupId, Model model) throws Exception {
		List<GroupScheduleVO> scheduleList = service.getScheduleList(groupId);
		
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
}
