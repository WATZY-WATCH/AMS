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

import ams.group.domain.GroupApplicationsVO;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;
import ams.group.domain.PageMaker;
import ams.group.domain.SearchCriteria;
import ams.group.service.GroupCommentService;
import ams.group.service.GroupService;
import ams.user.service.UserService;

@Controller
@RequestMapping("/group/**")
public class GroupController {
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Inject GroupService service;
	@Inject GroupCommentService commentService;
	@Inject UserService userService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String getCreate() throws Exception {
		logger.info("get /group/create......");
		return "group_create";
	}
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public int postCreate(@RequestBody GroupVO vo, Principal principal) throws Exception {
		logger.info("post /group/create.......");
		vo.setGroupMasterId(principal.getName());
		service.createGroup(vo);
		int groupId=vo.getGroupId();
		GroupMemberVO gmvo=new GroupMemberVO();
		gmvo.setGroupId(groupId);
		gmvo.setUserId(principal.getName());
		gmvo.setGroupAuthority("MASTER");
		service.createGroupMember(gmvo);
		return groupId;
	}
	@ResponseBody
	@RequestMapping(value = "/listApply", method = RequestMethod.POST)
	  public int listApply(@RequestBody GroupApplicationsVO vo) throws Exception {
		System.out.println("post listApply..............");
		return service.listApply(vo); 
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
		GroupApplicationsVO gavo= new GroupApplicationsVO();
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