package ams.user.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;

import ams.user.domain.UserVO;
import ams.user.service.Kakaoapi;
import ams.user.service.UserService;

@Controller
@RequestMapping("/oauth/**")
public class OAuthUserController {
	
	@Inject Kakaoapi KakaoAPI;
	@Inject UserService service;
	private static final Logger logger = LoggerFactory.getLogger(OAuthUserController.class);
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String postSignUp(UserVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post register");
		service.signupOAuth(vo);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public int postUser_modify(@RequestBody UserVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post user_modify");
		logger.info(vo.getUserName());
		return service.modifyUser(vo);
	}
	
	@ResponseBody
	@RequestMapping(value="/nameChk", method=RequestMethod.POST)
	public int postNameChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post nameChk");
		String userName = vo.getUserName();
		int res = service.nameChk(userName);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/emailChK", method=RequestMethod.POST)
	public int postEmailChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post emailChk.....");
		String userEmail = vo.getUserEmail();
		int res = service.emailChk(userEmail);
		return res;
	}
	
	@RequestMapping(value="/", method=RequestMethod.DELETE)
	public String postSignout(Principal principal, HttpSession session) throws Exception {
		logger.info("post kakao oauth singout......");
		UserVO vo = service.getUserInfo(principal.getName());
		JsonNode OAuthUser = KakaoAPI.postSignout(vo.getUserToken());
		String retId = OAuthUser.get("id").asText();
		if(retId != null) {
			service.signout(retId);
		}
		return "oauth_signout";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String postLogout(Principal principal, HttpSession session) throws Exception {
		logger.info("post kakao oauth logout......");
		UserVO vo = service.getUserInfo(principal.getName());
		JsonNode OAuthUser = KakaoAPI.postLogout(vo.getUserToken());
		String retId = OAuthUser.get("id").asText();
		if(retId != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
