package ams.group.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.PageMaker;
import ams.group.service.GroupCommentService;

@RestController
@RequestMapping("/comment")
public class GroupCommentController {
	
	@Inject private GroupCommentService service;
	
	@RequestMapping(value="/{groupId}", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody GroupCommentVO vo){
		System.out.println("post create.........");
		ResponseEntity<String> entity = null;
		try {
			service.createComment(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;	
	}
	
	@RequestMapping(value="/count/{groupId}", method = RequestMethod.POST)
	public ResponseEntity<Integer> count(@PathVariable("groupId") int groupId){
		System.out.println("post count.........");
		ResponseEntity<Integer> entity = null;
		int ret=0;
		try {
			ret=service.commentCount(groupId);
			entity = new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value="/count/{groupId}/{commentId}", method = RequestMethod.POST)
	public ResponseEntity<Integer> currentCount(@PathVariable("groupId") int groupId, @PathVariable("commentId") int commentId){
		System.out.println("post currentCount.........");
		ResponseEntity<Integer> entity = null;
		int ret=0;
		try {
			GroupCommentVO vo=new GroupCommentVO();
			vo.setCommentId(commentId);
			vo.setGroupId(groupId);
			ret=service.currentCommentCount(vo);
			entity = new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{groupId}/{curPage}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> list(@PathVariable("groupId") int groupId, @PathVariable("curPage") int curPage, Principal principal) throws JsonProcessingException {
		ResponseEntity<Map<String,Object>> entity=null;
		try {
			GroupCriteria cri = new GroupCriteria();
			cri.setPage(curPage);
			System.out.println("get list.........");
			PageMaker commentPageMaker = new PageMaker();
			commentPageMaker.setCri(cri);
			commentPageMaker.setTotalCount(service.commentCount(groupId));
			List<GroupCommentVO> gcvoList= service.commentList(groupId, cri);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("commentList", gcvoList);
			map.put("commentPageMaker", commentPageMaker);
			map.put("userId", principal.getName());
			map.put("curPage", curPage);
			entity=new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/{commentId}/{groupId}" ,method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("commentId") int commentId, @PathVariable("groupId") int groupId) {
		System.out.println("DELETE comment delete.........");
		ResponseEntity<String> entity = null;
		try {
			GroupCommentVO vo =new GroupCommentVO();
			vo.setGroupId(groupId);
			vo.setCommentId(commentId);
			service.deleteComment(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/{commentId}/{commentMsg}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("commentId") int commentId, @PathVariable("commentMsg") String commentMsg) {
		System.out.println("put comment update.........");
		ResponseEntity<String> entity = null;
		try {
			GroupCommentVO vo= new GroupCommentVO();
			vo.setCommentId(commentId);
			vo.setCommentMsg(commentMsg);
			service.updateComment(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		    entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
