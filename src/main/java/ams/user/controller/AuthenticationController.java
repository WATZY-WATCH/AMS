package ams.user.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

import ams.user.domain.UserVO;
import ams.user.service.Kakaoapi;
import ams.user.service.UserService;

@Controller
public class AuthenticationController {
	
	@Inject Kakaoapi KakaoAPI;
	@Inject UserService service;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String viewLoginForm() {
		return "user_login";
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String viewLogOut() {
		return "user_logout";
	}
	
	@RequestMapping(path="/klogin", method=RequestMethod.GET)
	public String kakaoAccess() throws UnsupportedEncodingException {
		final String CLIENT_ID = "718b94115712bb9ba0bde752892fae07";
		final String REDIRECT_URI = "http://localhost:8080/oauth";
		final String RequestUrl = "https://kauth.kakao.com/oauth/authorize?";
		StringBuffer url = new StringBuffer();
		SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
		url.append(RequestUrl);
		url.append("client_id=" + CLIENT_ID);
		url.append("&redirect_uri=" + REDIRECT_URI);
		url.append("&response_type=code");
		url.append("&state=" + state);
		return "redirect:" + url.toString();
	}
	
	@RequestMapping(path="/oauth", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
	public String kakaoLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) throws Exception {
		logger.info("Get UserInfo........");
		System.out.println(code);
		JsonNode at = KakaoAPI.getAccessToken(code, state);
		String access_token = at.get("access_token").asText();
//		String refresh_token = at.get("refresh_token").asText();
		
		System.out.println(at);
		
		JsonNode userInfo = KakaoAPI.getUserInfo(access_token);
		
		String userId = userInfo.get("id").asText();
		
		JsonNode properties = userInfo.get("properties");
		JsonNode kakao_account = userInfo.get("kakao_account");
		
		String userNickname = properties.get("nickname").asText();
		String userEmail = kakao_account.get("email").asText();
		
		int isSigned = service.idChk(userId);
		if(isSigned == 0) {
			UserVO vo = new UserVO();
			vo.setUserType("KAKAO");
			vo.setUserId(userId);
			vo.setUserPw("kakao");
			vo.setUserName(userNickname);
			vo.setUserEmail(userEmail);
			vo.setUserToken(access_token);
			service.signupOAuth(vo);
		} else {
			UserVO vo = service.getUserInfo(userId);
			vo.setUserToken(access_token);
			service.updateOAuthToken(vo);
		}
		
		System.out.println("controller access_token : " + access_token);
		System.out.println("login Controller : " + userInfo);
        return "redirect:/";
    }
}
