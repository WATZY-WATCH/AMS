package ams.group.controller;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import ams.group.domain.GroupScheduleVO;
import ams.group.service.GroupService;

@Controller
@RequestMapping("/group/**")
public class GroupScheduleController {

	@Inject GroupService service;
	
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
	
}
