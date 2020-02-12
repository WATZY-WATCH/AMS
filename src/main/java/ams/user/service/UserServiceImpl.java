package ams.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.user.dao.UserDAO;
import ams.user.domain.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Inject UserDAO dao;
	
	@Override
	public void signup(UserVO vo) throws Exception {
		dao.signup(vo);
	}
	
	@Override
	public int idChk(String userId) throws Exception {
		int res = dao.idChk(userId);
		return res;
	}
	
	@Override
	public int nameChk(String userName) throws Exception {
		int res = dao.nameChk(userName);
		return res;
	}
	
	@Override
	public int emailChk(String userEmail) throws Exception {
		int res = dao.emailChk(userEmail);
		return res;
	}
	
	@Override
	public int pwChk(UserVO vo) throws Exception {
		return dao.pwChk(vo);
	}
	
	@Override
	public int signout(String userId) throws Exception {
		return dao.signout(userId);
	}
	
	@Override
	public int modifyUser(UserVO vo) throws Exception {
		return dao.modifyUser(vo);
	}
	
	@Override
	public UserVO getUserInfo(String userId) throws Exception {
		return dao.getUserInfo(userId);
	}
	
	@Override
	public String findUser(String userId) throws Exception {
		return dao.findUser(userId);
	}
}
