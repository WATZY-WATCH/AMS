package ams.user.service;

import javax.inject.Inject;

import ams.user.dao.OAuthUserDAO;
import ams.user.domain.UserVO;

public class OAuthUserServiceImpl implements OAuthUserService {
	@Inject OAuthUserDAO dao;
	
	@Override
	public void signupOAuth(UserVO vo) throws Exception {
		dao.signupOAuth(vo);
	}
	
	@Override
	public int OAuthIdChk(String userId) throws Exception {
		return dao.OAuthIdChk(userId);
	}
	
	@Override
	public int OAuthNameChk(String userName) throws Exception {
		return dao.OAuthNameChk(userName);
	}
	
	@Override
	public int OAuthEmailChk(String userEmail) throws Exception {
		return dao.OAuthEmailChk(userEmail);
	}
	
	@Override
	public UserVO getOAuthUserInfo(String userId) throws Exception {
		return dao.getOAuthUserInfo(userId);
	}
	
	@Override
	public String getOAuthUserAuthority(String userId) throws Exception {
		return dao.getOAuthUserAuthority(userId);
	}
}
