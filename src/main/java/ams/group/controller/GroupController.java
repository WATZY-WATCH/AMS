package ams.group.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;
import ams.group.domain.PageMaker;
import ams.group.domain.SearchCriteria;
import ams.group.service.GroupCommentService;
import ams.group.service.GroupService;
import ams.user.service.UserService;

@RestController
@RequestMapping("/group/**")
public class GroupController {
	
	@Inject GroupService service;
	@Inject GroupCommentService commentService;
	@Inject UserService userService;
	

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<Integer> postCreate(@RequestBody GroupVO vo, Principal principal) throws Exception {
		System.out.println("post /group/create.......");
		ResponseEntity<Integer> entity = null;
		try {
			vo.setGroupMasterId(principal.getName());
			service.createGroup(vo);
			int groupId=vo.getGroupId();
			GroupMemberVO gmvo=new GroupMemberVO();
			gmvo.setGroupId(groupId);
			gmvo.setUserId(principal.getName());
			gmvo.setGroupAuthority("MASTER");
			service.createGroupMember(gmvo);
			entity = new ResponseEntity<Integer>(groupId, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;	
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<Integer> listApply(@RequestBody GroupApplicationVO vo) throws Exception {
		System.out.println("post listApply..............");
		ResponseEntity<Integer> entity = null;
		try {
			entity = new ResponseEntity<Integer>(service.listApply(vo), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;	
	}
}