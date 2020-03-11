package ams.group.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;
import ams.group.domain.PageMaker;
import ams.group.domain.SearchCriteria;
import ams.group.service.GroupCommentService;
import ams.group.service.GroupService;
import ams.user.service.UserService;

@Controller
@RequestMapping("/group/**")
public class GroupViewController {

	@Inject GroupService service;
	@Inject GroupCommentService commentService;
	@Inject UserService userService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String getCreate() throws Exception {

		return "group_create";
	}
	
	@RequestMapping(value = "/listRead", method = RequestMethod.GET)
	  public String listRead(@RequestParam("groupId") int groupId, @ModelAttribute("cri") SearchCriteria cri, Model model,Principal principal) throws Exception {
		System.out.println("get listRead..............");
		GroupVO gvo=service.listRead(groupId);
		model.addAttribute("GroupVO",gvo);
		model.addAttribute("UserVO",userService.getUserInfo(gvo.getGroupMasterId()));
		String userId=principal.getName();
		model.addAttribute("userId",userId);
		GroupMemberVO gmvo=new GroupMemberVO();
		gmvo.setUserId(userId);
		gmvo.setGroupId(groupId);
		int ret=service.memberChk(gmvo);
		if(ret>=1) model.addAttribute("memberChk",1);
		else model.addAttribute("memberChk",0);
		model.addAttribute("UserVO", userService.getUserInfo(userId));
		GroupApplicationVO gavo= new GroupApplicationVO();
		gavo.setGroupId(groupId);
		gavo.setUserId(userId);
		model.addAttribute("listApplyChk", service.listApplyChk(gavo));
	    return "group_list_read";
	}
	
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public String listCri(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		System.out.println("get listCri..............");
		List<GroupVO> gvoList = service.listSearch(cri);
		model.addAttribute("list",gvoList);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker",pageMaker);
		
		return "group_list";
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#groupId, principal.username)")
	@RequestMapping(value="/mapAPI", method=RequestMethod.GET)
	public String getMapAPI(@RequestParam("groupId") int groupId, Model model) throws Exception {
		String groupName = service.listRead(groupId).getGroupName();
		model.addAttribute("groupId", groupId);
		model.addAttribute("groupName", groupName);
		return "group_map";
	}
}