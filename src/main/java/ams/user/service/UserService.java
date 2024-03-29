package ams.user.service;

import java.util.List;

import ams.group.domain.GroupScheduleVO;
import ams.group.domain.GroupVO;
import ams.user.domain.UserVO;

public interface UserService {
	public void signup(UserVO vo) throws Exception;
	public void signupOAuth(UserVO vo) throws Exception;
	public String getAuthority(String userId) throws Exception;
	public int idChk(String userId) throws Exception;
	public int nameChk(String userName) throws Exception;
	public int emailChk(String userEmail) throws Exception;
	public int pwChk(UserVO vo) throws Exception;
	public int signout(String userId) throws Exception;
	public int modifyUser(UserVO vo) throws Exception;
	public int modifyUserPw(UserVO vo) throws Exception;
	public int updateOAuthToken(UserVO vo) throws Exception;
	public UserVO getUserInfo(String userId) throws Exception;
	public String findUser(String userId) throws Exception;
	public List<GroupVO> findJoinedGroup(String userId) throws Exception;
	public List<GroupScheduleVO> showWeekSchedule(UserVO vo) throws Exception;
}
