package ams.user.dao;

import ams.user.domain.OAuthUserVO;

public interface OAuthUserDAO {
	public void signupOAuth(OAuthUserVO vo) throws Exception;
	public int OAuthIdChk(String userId) throws Exception;
	public int OAuthNameChk(String userName) throws Exception;
	public int OAuthEmailChk(String userEmail) throws Exception;
	public OAuthUserVO getOAuthUserInfo(String userId) throws Exception;
	public String getOAuthUserAuthority(String userId) throws Exception;
	public int signoutOAuth(String userId) throws Exception;
	public int modifyOAuthUser(OAuthUserVO vo) throws Exception;
	public int updateOAuthToken(OAuthUserVO vo) throws Exception;
}
