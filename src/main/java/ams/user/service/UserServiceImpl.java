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
	public String findUser(String userId) throws Exception {
		return dao.findUser(userId);
	}
}
