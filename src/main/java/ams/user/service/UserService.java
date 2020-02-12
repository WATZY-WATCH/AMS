package ams.user.service;

import ams.user.domain.UserVO;

public interface UserService {
	public void signup(UserVO vo) throws Exception;
	public int idChk(String userId) throws Exception;
	public int nameChk(String userName) throws Exception;
	public int emailChk(String userEmail) throws Exception;
	public int pwChk(UserVO vo) throws Exception;
	public int signout(String userId) throws Exception;
	public int modifyUser(UserVO vo) throws Exception;
	public UserVO getUserInfo(String userId) throws Exception;
	public String findUser(String userId) throws Exception;
}
