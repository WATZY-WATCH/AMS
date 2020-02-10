package ams.user.service;

import ams.user.domain.UserVO;

public interface UserService {
	public void signup(UserVO vo) throws Exception;
	public int idChk(String userId) throws Exception;
	public String findUser(String userId) throws Exception;
}
