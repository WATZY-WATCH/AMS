package ams.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.user.dao.OAuthUserDAO;
import ams.user.domain.OAuthUserVO;

@Service
public class OAuthUserServiceImpl implements OAuthUserService {
	@Inject OAuthUserDAO dao;
	
	@Override
	public void signupOAuth(OAuthUserVO vo) throws Exception {
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
	public OAuthUserVO getOAuthUserInfo(String userId) throws Exception {
		return dao.getOAuthUserInfo(userId);
	}
	
	@Override
	public String getOAuthUserAuthority(String userId) throws Exception {
		return dao.getOAuthUserAuthority(userId);
	}
	
	@Override
	public int signoutOAuth(String userId) throws Exception {
		return dao.signoutOAuth(userId);
	}
	
	@Override
	public int modifyOAuthUser(OAuthUserVO vo) throws Exception {
		return dao.modifyOAuthUser(vo);
	}
	
	@Override
	public int updateOAuthToken(OAuthUserVO vo) throws Exception {
		return dao.updateOAuthToken(vo);
	}
}
