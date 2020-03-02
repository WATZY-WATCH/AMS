package ams.group.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupVO;
import ams.group.domain.PageMaker;
import ams.group.domain.SearchCriteria;
import ams.group.service.GroupManageService;


@Controller
@RequestMapping("groupManage/**")
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
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#groupId, principal.username)")
	@RequestMapping(value="/master", method=RequestMethod.GET)
	public String master(@RequestParam int groupId, @ModelAttribute("cri") GroupCriteria cri, Model model, Principal principal) throws Exception {
		System.out.println("get master..............");
		List<GroupApplicationVO> gavoList = service.masterApplicationList(groupId, cri);
		model.addAttribute("gavoList",gavoList);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.masterApplicationCount(groupId));
		model.addAttribute("pageMaker",pageMaker);
		model.addAttribute("groupId",groupId);
		return "group_manage_master";
	}
	
	@RequestMapping(value="/masterRead", method=RequestMethod.GET)
	public String masterRead(@RequestParam int applicationId, @ModelAttribute("cri") GroupCriteria cri, Model model) throws Exception {
		System.out.println("get masterRead..............");
		GroupApplicationVO gavo = service.masterApplicationRead(applicationId);
		model.addAttribute("gavo",gavo);
		model.addAttribute("cri",cri);
		return "group_manage_master_read";
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#vo.groupId, principal.username)")
	@ResponseBody
	@RequestMapping(value="/masterAccept", method = RequestMethod.POST)
	public int masterAccept(@RequestBody GroupApplicationVO vo){
		System.out.println("post masterAccept.........");
		int ret=0;
		try {
			service.masterAccept(vo);
			ret=service.applicationDelete(vo.getApplicationId());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#vo.groupId, principal.username)")
	@ResponseBody
	@RequestMapping(value="/masterReject", method = RequestMethod.POST)
	public int masterReject(@RequestBody GroupApplicationVO vo){
		System.out.println("post masterAccept.........");
		int ret=0;
		try {
			ret=service.applicationDelete(vo.getApplicationId());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}