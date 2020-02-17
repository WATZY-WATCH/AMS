package ams.user.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;

import ams.user.domain.OAuthUserVO;
import ams.user.service.Kakaoapi;
import ams.user.service.OAuthUserService;

@Controller
@RequestMapping("/oauth/user/**")
public class OAuthUserController {
	
	@Inject Kakaoapi KakaoAPI;
	@Inject OAuthUserService service;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String getSignUp() throws Exception {
		logger.info("get register");
		return "user_signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignUp(OAuthUserVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post register");
		service.signupOAuth(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String getUser_modify(Principal principal, Model model) throws Exception {
		logger.info("get user_modify");
		OAuthUserVO userInfo=service.getOAuthUserInfo(principal.getName());
		model.addAttribute("setName", userInfo.getUserName());
		model.addAttribute("setEmail", userInfo.getUserEmail());
		model.addAttribute("userId",principal.getName());
		logger.info("user :"+userInfo.getUserName());
		return "user_modify";
	}
	
	@ResponseBody
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public int postUser_modify(@RequestBody OAuthUserVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post user_modify");
		logger.info(vo.getUserName());
		return service.modifyOAuthUser(vo);
	}
	
	@ResponseBody
	@RequestMapping(value="/nameChk", method=RequestMethod.POST)
	public int postNameChk(@RequestBody OAuthUserVO vo) throws Exception {
		logger.info("post nameChk");
		String userName = vo.getUserName();
		int res = service.OAuthNameChk(userName);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/emailChK", method=RequestMethod.POST)
	public int postEmailChk(@RequestBody OAuthUserVO vo) throws Exception {
		logger.info("post emailChk.....");
		String userEmail = vo.getUserEmail();
		int res = service.OAuthEmailChk(userEmail);
		return res;
	}
	
	@RequestMapping(value="/signout", method=RequestMethod.GET)
	public String postSignout(Principal principal, HttpSession session) throws Exception {
		logger.info("post kakao oauth singout......");
		OAuthUserVO vo = service.getOAuthUserInfo(principal.getName());
		JsonNode OAuthUser = KakaoAPI.postSignout(vo.getAccessToken());
		String retId = OAuthUser.get("id").asText();
		if(retId != null) {
			service.signoutOAuth(retId);
		}
		return "oauth_signout";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String postLogout(Principal principal, HttpSession session) throws Exception {
		logger.info("post kakao oauth logout......");
		OAuthUserVO vo = service.getOAuthUserInfo(principal.getName());
		JsonNode OAuthUser = KakaoAPI.postLogout(vo.getAccessToken());
		String retId = OAuthUser.get("id").asText();
		if(retId != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
