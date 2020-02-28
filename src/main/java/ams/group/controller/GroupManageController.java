package ams.group.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ams.group.domain.GroupVO;

import ams.group.service.GroupManageService;


@Controller
@RequestMapping("group/manage/**")
public class GroupManageController {
	private static final Logger logger = LoggerFactory.getLogger(GroupManageController.class);
	
	@Inject GroupManageService service;
	
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Principal principal, Model model) throws Exception {
		List<GroupVO> gvoMasterList = service.masterList(principal.getName());
		List<GroupVO> gvoMemberList = service.memberList(principal.getName());
		List<GroupVO> gvoApplicationList = service.applicationList(principal.getName());
		model.addAttribute("masterList",gvoMasterList);
		model.addAttribute("memberList",gvoMemberList);
		model.addAttribute("applicationList",gvoApplicationList);
		return "group_manage_home";
	}
	
}