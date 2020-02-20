package ams.group.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping("/group/**")
public class GroupScheduleController {

	@ResponseBody
	@RequestMapping(value="/saveSchedule", method=RequestMethod.POST)
	public int saveLocationInfo(@RequestBody JsonNode location) {
		/* 스케줄 저장 기능 */
		return 0;
	}
	
}
