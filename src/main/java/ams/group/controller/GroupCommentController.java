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
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public void create(@RequestBody GroupCommentVO vo){
		System.out.println("post create.........");
		try {
			service.createComment(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/count", method = RequestMethod.POST)
	public int count(@RequestBody String groupId){
		System.out.println("post count.........");
		int ret=0;
		try {
			ret=service.commentCount(Integer.parseInt(groupId));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@RequestMapping(value="/currentCount", method = RequestMethod.POST)
	public int currentCount(@RequestBody GroupCommentVO vo){
		System.out.println("post currentCount.........");
		int ret=0;
		try {
			ret=service.currentCommentCount(vo);
			System.out.println("ret: "+ret);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
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
	@RequestMapping(value="delete" ,method = RequestMethod.DELETE)
	public int delete(@RequestBody GroupCommentVO vo) {
		System.out.println("get delete.........");
		int ret=0;
		try {
			ret=service.deleteComment(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	@RequestMapping(value="update", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public int update(@RequestBody GroupCommentVO vo) {
		System.out.println("get update.........");
		int ret=0;
		try {
			ret=service.updateComment(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
