package ams.group.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
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
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;
import ams.group.domain.PageMaker;
import ams.group.domain.SearchCriteria;
import ams.group.service.GroupManageService;


@Controller
@RequestMapping("groupManage/**")
public class GroupManageViewController {
	
	@Inject GroupManageService service;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Principal principal, Model model) throws Exception {
		System.out.println("get groupManage/home..............");
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
		model.addAttribute("userId",principal.getName());
		return "group_manage_master";
	}

	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#groupId, principal.username)")
	@RequestMapping(value="/masterRead", method=RequestMethod.GET)
	public String masterRead(@RequestParam int applicationId, @RequestParam int groupId, @ModelAttribute("cri") GroupCriteria cri, Model model) throws Exception {
		System.out.println("get masterRead..............");
		GroupApplicationVO gavo = service.masterApplicationRead(applicationId);
		model.addAttribute("gavo",gavo);
		model.addAttribute("cri",cri);
		return "group_manage_master_read";
	}
	
	@RequestMapping(value="/memberRead", method=RequestMethod.GET)
	public String memberRead(@RequestParam int groupId, Model model, Principal principal) throws Exception {
		System.out.println("get memberRead..............");
		GroupMemberVO gmvo = service.selectMember(groupId, principal.getName());
		GroupVO gvo =service.selectGroup(groupId);
		model.addAttribute("GroupMemberVO",gmvo);
		model.addAttribute("GroupVO",gvo);
		return "group_manage_member_read";
	}
	
	@RequestMapping(value="/applicationRead", method=RequestMethod.GET)
	public String applicationRead(@RequestParam int groupId, Model model, Principal principal) throws Exception {
		System.out.println("get applicationRead..............");
		GroupApplicationVO avo = service.seleteApplication(groupId, principal.getName());
		GroupVO gvo =service.selectGroup(groupId);
		model.addAttribute("GroupApplicationVO",avo);
		model.addAttribute("GroupVO",gvo);
		return "group_manage_application_read";
	}
	
}