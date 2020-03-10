package ams.user.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ams.group.domain.GroupVO;
import ams.user.domain.UserVO;
import ams.user.service.UserService;

@Controller
@RequestMapping("/user/**")
public class UserViewController {
	private static final Logger logger = LoggerFactory.getLogger(UserViewController.class);
	
	@Inject UserService service;
	@Inject BCryptPasswordEncoder pwdEncoder;
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String getSignUp() throws Exception {
		logger.info("get register");
		return "user_signup";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String getUser_modify(Principal principal, Model model) throws Exception {
		logger.info("get user_modify");
		System.out.println("get user_modify");
		UserVO userInfo=service.getUserInfo(principal.getName());
		model.addAttribute("setName", userInfo.getUserName());
		model.addAttribute("setEmail", userInfo.getUserEmail());
		model.addAttribute("userId",principal.getName());
		logger.info("user :"+userInfo.getUserName());
		return "user_modify";
	}
	
	@RequestMapping(value="/signout", method=RequestMethod.GET)
	public String getSignOut(Principal principal, Model model) throws Exception {
		logger.info("get signout.....");
		model.addAttribute("userId",principal.getName());
		return "user_signout";
	}
	
	@RequestMapping(value="/modifyPw", method=RequestMethod.GET)
	public String getModifyPw(Principal principal, Model model) throws Exception {
		logger.info("get Modify Password......");
		model.addAttribute("userId",principal.getName());
		return "user_modify_pw";
	}
	
	@RequestMapping(value="/modifyPwSuccess", method=RequestMethod.GET)
	public String getModifyPw() throws Exception {
		logger.info("Success Modify Password......");
		return "user_modify_pw_success";
	}
	
	@RequestMapping(value="/myPage", method=RequestMethod.GET)
	public String getJoinedGroup(Principal principal, Model model) throws Exception {
		logger.info("get my page.....");
		String userId = principal.getName();
		List<GroupVO> myGroups = service.findJoinedGroup(userId);
		model.addAttribute("gList", myGroups);
		return "user_mypage";
	}
}
