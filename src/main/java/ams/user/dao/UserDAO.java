package ams.user.dao;

import ams.user.domain.UserVO;

public interface UserDAO {
	public void signup(UserVO vo) throws Exception;
	public int idChk(String userId) throws Exception;
	public String findUser(String userId) throws Exception;
}
