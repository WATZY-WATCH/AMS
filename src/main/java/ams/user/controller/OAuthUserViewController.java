package ams.user.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ams.user.domain.UserVO;
import ams.user.service.UserService;

@Controller
@RequestMapping("/oauth/user/**")
public class OAuthUserViewController {
	
	@Inject UserService service;
	private static final Logger logger = LoggerFactory.getLogger(OAuthUserViewController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getSignUp() throws Exception {
		logger.info("get register");
		return "oauth_signup";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String getUser_modify(Principal principal, Model model) throws Exception {
		logger.info("get user_modify");
		UserVO userInfo=service.getUserInfo(principal.getName());
		model.addAttribute("setName", userInfo.getUserName());
		model.addAttribute("setEmail", userInfo.getUserEmail());
		model.addAttribute("userId",principal.getName());
		logger.info("user :"+userInfo.getUserName());
		return "user_modify";
	}
}
