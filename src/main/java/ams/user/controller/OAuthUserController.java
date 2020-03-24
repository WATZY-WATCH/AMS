package ams.user.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/oauth/user/**")
public class OAuthUserController {
	
	@Inject Kakaoapi KakaoAPI;
	@Inject UserService service;
	private static final Logger logger = LoggerFactory.getLogger(OAuthUserController.class);
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> postSignUp(@RequestBody UserVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post register");
		ResponseEntity<String> entity = null;
		try {
			vo.setUserPw("KAKAO");
			service.signupOAuth(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
			List<GrantedAuthority> roles = new ArrayList<>(1);	
			String role = service.getAuthority(vo.getUserId());	
			roles.add(new SimpleGrantedAuthority(role));
			roles.add(new SimpleGrantedAuthority("KAKAO"));
			Authentication auth = new UsernamePasswordAuthenticationToken(vo.getUserId(), null, roles);	
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.DELETE)
	public ResponseEntity<String> postSignout(Principal principal, HttpSession session) {
		logger.info("post kakao oauth singout......");
		ResponseEntity<String> entity = null;
		try {
			UserVO vo = service.getUserInfo(principal.getName());
			JsonNode OAuthUser = KakaoAPI.postSignout(vo.getUserToken());
			if(OAuthUser.has("id")) {
				String retId = OAuthUser.get("id").asText();
				service.signout(retId);
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				session.invalidate();
			} else {
				entity = new ResponseEntity<String>("FAIL", HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="/name", method=RequestMethod.POST)
	public int postNameChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post nameChk");
		String userName = vo.getUserName();
		int res = service.nameChk(userName);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/email", method=RequestMethod.POST)
	public int postEmailChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post emailChk.....");
		String userEmail = vo.getUserEmail();
		int res = service.emailChk(userEmail);
		return res;
	}
	
}
