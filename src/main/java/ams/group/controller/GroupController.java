package ams.group.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ams.group.domain.GroupVO;
import ams.group.service.GroupService;

@Controller
@RequestMapping("/group/**")
public class GroupController {
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Inject GroupService service;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String getCreate() throws Exception {
		logger.info("get /group/create......");
		return "group_create";
	}
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public int postCreate(@RequestBody GroupVO vo) throws Exception {
		logger.info("post /group/create.......");
		return service.createGroup(vo);
	}	
	
}