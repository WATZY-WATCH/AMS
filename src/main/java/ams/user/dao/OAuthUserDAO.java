package ams.user.dao;

import ams.user.domain.UserVO;

public interface OAuthUserDAO {
	public void signupOAuth(UserVO vo) throws Exception;
	public int OAuthIdChk(String userId) throws Exception;
	public int OAuthNameChk(String userName) throws Exception;
	public int OAuthEmailChk(String userEmail) throws Exception;
	public UserVO getOAuthUserInfo(String userId) throws Exception;
	public String getOAuthUserAuthority(String userId) throws Exception;
}
