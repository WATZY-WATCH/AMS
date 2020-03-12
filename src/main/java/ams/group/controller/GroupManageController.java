package ams.group.controller;

import java.security.Principal;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupMemberVO;
import ams.group.service.GroupManageService;


@RestController
@RequestMapping("groupManage/**")
public class GroupManageController {
	private static final Logger logger = LoggerFactory.getLogger(GroupManageController.class);
	
	@Inject GroupManageService service;
	
	@RequestMapping(value="/master", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> masterDelete(@RequestBody GroupMemberVO vo, Principal principal){
		System.out.println("post masterDelete.........");
		ResponseEntity<Integer> entity = null;
		try {
			entity = new ResponseEntity<Integer>(service.masterDelete(vo), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#vo.groupId, principal.username)")
	@RequestMapping(value="/master", method = RequestMethod.POST)
	public ResponseEntity<Integer> masterAccept(@RequestBody GroupApplicationVO vo){
		System.out.println("post masterAccept.........");
		ResponseEntity<Integer> entity = null;
		try {
			service.masterAccept(vo);
			entity = new ResponseEntity<Integer>(service.applicationDelete(vo.getApplicationId()), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PreAuthorize("@customAuthorizationHandler.isAdmin(#vo.groupId, principal.username)")
	@RequestMapping(value="/master/application", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> masterReject(@RequestBody GroupApplicationVO vo){
		System.out.println("post masterReject.........");
		ResponseEntity<Integer> entity = null;
		try {
			entity = new ResponseEntity<Integer>(service.applicationDelete(vo.getApplicationId()), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/member", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> leaveGroup(@RequestBody GroupMemberVO vo){
		System.out.println("post leaveGroup.........");
		ResponseEntity<Integer> entity = null;
		try {
			entity = new ResponseEntity<Integer>(service.deleteMember(vo), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/application", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> applicationDelete(@RequestBody GroupApplicationVO vo){
		System.out.println("post applicationDelete.........");
		ResponseEntity<Integer> entity = null;
		try {
			entity = new ResponseEntity<Integer>(service.deleteApplication(vo), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}