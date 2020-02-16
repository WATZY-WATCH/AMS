package ams.user.service;

import ams.user.domain.UserVO;

public interface OAuthUserService {
	public void signupOAuth(UserVO vo) throws Exception;
	public int OAuthIdChk(String userId) throws Exception;
	public int OAuthNameChk(String userName) throws Exception;
	public int OAuthEmailChk(String userEmail) throws Exception;
	public UserVO getOAuthUserInfo(String userId) throws Exception;
	public String getOAuthUserAuthority(String userId) throws Exception;
	public int signoutOAuth(String userId) throws Exception;
	public int modifyOAuthUser(UserVO vo) throws Exception;
}
