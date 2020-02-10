package ams.user.test;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ams.user.domain.UserVO;
import ams.user.service.UserService;

@Controller
@RequestMapping("/user/**")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject UserService service;
	@Inject BCryptPasswordEncoder pwdEncoder;
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String getSignUp() throws Exception {
		logger.info("get register");
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignUp(UserVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post register");
		String inputPwd = vo.getUserPw();
		String pwd = pwdEncoder.encode(inputPwd);
		vo.setUserPw(pwd);
		service.signup(vo);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/idChk", method=RequestMethod.POST)
	public int postIdChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post idChk");
		String userId = vo.getUserId();
		int res = service.idChk(userId);
		return res;
	}
}
