package ams.user.dao;

import ams.user.domain.UserVO;

public interface UserDAO {
	public void signup(UserVO vo) throws Exception;
	public int idChk(String userId) throws Exception;
	public int nameChk(String userName) throws Exception;
	public int emailChk(String userName) throws Exception;
	public int pwChk(UserVO vo) throws Exception;
	public int signout(String userId) throws Exception;
	public int modifyUser(UserVO vo) throws Exception;
	public UserVO getUserInfo(String userId) throws Exception;
	public UserVO getOAuthUserInfo(String userId) throws Exception;
	public String getOAuthUserAuthority(String userId) throws Exception;
	public String findUser(String userId) throws Exception;
}
