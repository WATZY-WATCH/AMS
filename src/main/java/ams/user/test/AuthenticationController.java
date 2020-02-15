package ams.user.test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

import ams.user.service.Kakaoapi;

@Controller
public class AuthenticationController {
	
	@Inject Kakaoapi KakaoAPI;
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
		final String CLIENT_ID = "";
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
	public String kakaoLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) throws UnsupportedEncodingException {
		logger.info("Get UserInfo........");
		System.out.println(code);
		JsonNode at = KakaoAPI.getAccessToken(code, state);
		String access_token = at.get("access_token").asText();
		HashMap<String, Object> userInfo = KakaoAPI.getUserInfo(access_token);
		System.out.println("controller access_token : " + access_token);
		System.out.println("login Controller : " + userInfo);
		if (userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("nickname", userInfo.get("nickname"));
			session.setAttribute("access_Token", access_token);
		}

        return "redirect:/";
    }
}
