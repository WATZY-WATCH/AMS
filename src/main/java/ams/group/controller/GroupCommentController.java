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
			System.out.println("cri.getPage( ): " +cri.getPage());
			System.out.println("cri.getPageStart( ): " +cri.getPageStart());
			PageMaker commentPageMaker = new PageMaker();
			commentPageMaker.setCri(cri);
			commentPageMaker.setTotalCount(service.commentCount(groupId));

			List<GroupCommentVO> gcvoList= service.commentList(groupId, cri);
			System.out.println(gcvoList);
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
		int ret=0;
		try {
			ret=service.updateComment(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
//	@RequestMapping(value="/all/{commentId}", method = RequestMethod.GET)
//	public ResponseEntity<List<GroupCommentVO>> list(@PathVariable("commentId") int commentId){
//		
//		ResponseEntity<List<GroupCommentVO>> entity = null;
//		try {
//			entity=new ResponseEntity<>(service.commentList(commentId),HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		return entity;
//	}
	
	@RequestMapping(value="/{commentId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("commentId") int commentId, @RequestBody GroupCommentVO vo){
		ResponseEntity<String> entity = null;
		try {
			vo.setCommentId(commentId);
			service.updateComment(vo);
			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
//	@RequestMapping(value="/{commentId}", method = RequestMethod.DELETE)
//	public ResponseEntity<String> remove(@PathVariable("commentId") int commentId){
//		ResponseEntity<String> entity = null;
//		try {
//			service.deleteComment(commentId);
//			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//		}
//		return entity;
//	}
	
//	@RequestMapping(value="/{commentId}/{page}", method = RequestMethod.GET)
//	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("commentId") int commentId, @PathVariable("page") int page){
//		ResponseEntity<Map<String, Object>> entity = null;
//		try {
//			GroupCriteria cri = new GroupCriteria();
//			cri.setPage(page);
//			
//			PageMaker pageMaker = new PageMaker();
//			pageMaker.setCri(cri);
//			
//			Map<String, Object> map = new HashMap<String, Object>();
//			//List<GroupCommentVO> list = service.commentList(groupId);
//			
//			map.put("list", list);
//			
//			int commentCount = service.commentCount(commentId);
//			pageMaker.setTotalCount(commentCount);
//			
//			map.put("pageMakre",pageMaker);
//			
//			entity=new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		} catch(Exception e) {
//			e.printStackTrace();
//			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
//		}
//		return entity;
//	}
}
