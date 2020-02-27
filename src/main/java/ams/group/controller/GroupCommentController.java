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
	
	@ResponseBody
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public void create(@RequestBody GroupCommentVO vo){
		System.out.println("get create.........");
		try {
			service.createComment(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/list")
	public ModelAndView list(@RequestParam int groupId, @RequestParam(defaultValue="1") int curPage, ModelAndView mav, Principal principal) throws JsonProcessingException {
		try {
			GroupCriteria cri = new GroupCriteria();
			cri.setPage(curPage);
			System.out.println("get list.........");
			PageMaker commentPageMaker = new PageMaker();
			commentPageMaker.setCri(cri);
			commentPageMaker.setTotalCount(service.commentCount(groupId));
			List<GroupCommentVO> gcvoList= service.commentList(groupId, cri);
			mav.setViewName("group_list_comment");
			mav.addObject("commentList", gcvoList);
			mav.addObject("commentPageMaker", commentPageMaker);
			mav.addObject("userId", principal.getName());
			mav.addObject("curPage", curPage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	@ResponseBody
	@RequestMapping("delete")
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
	@ResponseBody
	@RequestMapping("update")
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
