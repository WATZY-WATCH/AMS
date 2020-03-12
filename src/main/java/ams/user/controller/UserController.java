package ams.user.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ams.user.domain.UserVO;
import ams.user.service.UserService;

@RestController
@RequestMapping("/user/**")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject UserService service;
	@Inject BCryptPasswordEncoder pwdEncoder;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void postSignUp(UserVO vo, HttpServletResponse response) throws Exception {
		logger.info("post register");
		try {
			String inputPwd = vo.getUserPw();
			String pwd = pwdEncoder.encode(inputPwd);
			vo.setUserPw(pwd);
			service.signup(vo);
			response.sendRedirect("/");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/id/{userId}", method=RequestMethod.GET)
	public ResponseEntity<Integer> postIdChk(@PathVariable("userId") String userId) throws Exception {
		logger.info("post idChk");
		ResponseEntity<Integer> entity = null;
		int ret=0;
		try {
			ret=service.idChk(userId);
			entity = new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<String> postUser_modify(@RequestBody UserVO vo) throws Exception {
		logger.info("post user_modify");
		ResponseEntity<String> entity = null;
		try {
			service.modifyUser(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value="/name", method=RequestMethod.POST)
	public ResponseEntity<Integer> postNameChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post nameChk");
		ResponseEntity<Integer> entity = null;
		int ret=0;
		try {
			ret=service.nameChk(vo.getUserName());
			entity = new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/email", method=RequestMethod.POST)
	public ResponseEntity<Integer> postEmailChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post emailChk.....");
		ResponseEntity<Integer> entity = null;
		int ret=0;
		try {
			ret=service.emailChk(vo.getUserEmail());
			entity = new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value="/pw", method=RequestMethod.POST)
	public ResponseEntity<Boolean> pwChk(@RequestBody UserVO vo) throws Exception {
		logger.info("post pwChk.....");
		ResponseEntity<Boolean> entity = null;
		try {
			String userPw = vo.getUserPw();
			String pwd = pwdEncoder.encode(userPw);
			vo.setUserPw(pwd);
			String hashPw=service.getUserInfo(vo.getUserId()).getUserPw();
			entity = new ResponseEntity<Boolean>(pwdEncoder.matches(userPw, hashPw), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value="/pw", method=RequestMethod.PUT)
	public ResponseEntity<Integer> postModifyPw(@RequestBody UserVO vo) throws Exception {
		logger.info("post Modify Password......");
		ResponseEntity<Integer> entity = null;
		int ret=0;
		try {
			String userPw = vo.getUserPw();
			String pwd = pwdEncoder.encode(userPw);
			vo.setUserPw(pwd);
			ret=service.modifyUserPw(vo);
			entity = new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/", method=RequestMethod.DELETE)
	public ResponseEntity<Integer> postSignout(@RequestBody UserVO vo,HttpSession session) throws Exception {
		logger.info("post signout......");
		ResponseEntity<Integer> entity = null;
		int ret=0;
		try {
			ret= service.signout(vo.getUserId());
			if(ret>0) {
				session.invalidate();
			}
			entity = new ResponseEntity<Integer>(ret, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
