package ams.group.controller;

import java.security.Principal;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;
import ams.group.domain.PageMaker;
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
	public int postCreate(@RequestBody GroupVO vo, Principal principal) throws Exception {
		logger.info("post /group/create.......");
		service.createGroup(vo);
		int groupId=vo.getGroupId();
		System.out.println("ID: "+principal.getName());
		GroupMemberVO gmvo=new GroupMemberVO();
		gmvo.setGroupId(groupId);
		gmvo.setUserId(principal.getName());
		gmvo.setGroupAuthority("MASTER");
		service.createGroupMember(gmvo);
		return groupId;
	}
	
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public String listAll(GroupCriteria cri, Model model) throws Exception {
		System.out.println("get listCri..............");
		model.addAttribute("list",service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countPaging(cri));
		model.addAttribute("pageMaker",pageMaker);
		
		return "group_list";
	}
	
	@RequestMapping(value="/mapAPI", method=RequestMethod.GET)
	public String getMapAPI() {
		return "group_map";
	}
}