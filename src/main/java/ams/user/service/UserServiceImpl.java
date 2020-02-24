package ams.user.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.group.domain.GroupScheduleVO;
import ams.group.domain.GroupVO;
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
	public int modifyUserPw(UserVO vo) throws Exception {
		return dao.modifyUserPw(vo);
	}
	
	@Override
	public UserVO getUserInfo(String userId) throws Exception {
		return dao.getUserInfo(userId);
	}
	
	@Override
	public String findUser(String userId) throws Exception {
		return dao.findUser(userId);
	}
	
	@Override
	public List<GroupVO> findJoinedGroup(String userId) throws Exception {
		return dao.findJoinedGroup(userId);
	}
	
	@Override
	public List<GroupScheduleVO> showWeekSchedule(UserVO vo) throws Exception {
		return dao.showWeekSchedule(vo);
	}
}
