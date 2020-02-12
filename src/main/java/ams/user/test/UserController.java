package ams.user.test;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value="/user_modify", method=RequestMethod.GET)
	public String getUser_modify(Principal principal, Model model) throws Exception {
		logger.info("get user_modify");
		UserVO userInfo=service.getUserInfo(principal.getName());
		model.addAttribute("setName", userInfo.getUserName());
		model.addAttribute("setEmail", userInfo.getUserEmail());
		model.addAttribute("userId",principal.getName());
		return "user_modify";
	}
	
	@ResponseBody
	@RequestMapping(value="/user_modify", method=RequestMethod.POST)
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
	@RequestMapping(value="/emailChk", method=RequestMethod.POST)
	public int postEmailChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post emailChk.....");
		String userEmail = vo.getUserEmail();
		int res = service.emailChk(userEmail);
		return res;
	}
	
	@RequestMapping(value="/signout", method=RequestMethod.GET)
	public String getSignOut(Principal principal, Model model) throws Exception {
		logger.info("get signout.....");
		model.addAttribute("userId",principal.getName());
		return "signout";
	}
	
	@ResponseBody
	@RequestMapping(value="/pwChk", method=RequestMethod.POST)
	public boolean postSignOut(@RequestBody UserVO vo) throws Exception {
		logger.info("post pwChk......");
		String userPw = vo.getUserPw();
		String pwd = pwdEncoder.encode(userPw);
		vo.setUserPw(pwd);
		String hashPw=service.getUserInfo(vo.getUserId()).getUserPw();
		return pwdEncoder.matches(userPw, hashPw);
	}
	
	@ResponseBody
	@RequestMapping(value="/signout", method=RequestMethod.POST)
	public int postSignout(@RequestBody UserVO vo,HttpSession session) throws Exception {
		logger.info("post signout......");
		int ret= service.signout(vo.getUserId());
		if(ret>0) {
			session.invalidate();
		}
		return ret;
	}
	
}
