package ams.user.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.user.domain.OAuthUserVO;

@Repository
public class OAuthUserDAOImpl implements OAuthUserDAO {

	@Inject SqlSession sql;
	
	private static final String ns = "ams.user.mapper.userMapper";
	
	@Override
	public void signupOAuth(OAuthUserVO vo) throws Exception {
		sql.insert(ns+".signupOAuth", vo);
		sql.insert(ns+".setOAuthAuthority", vo);
	}
	
	@Override
	public int OAuthIdChk(String userId) throws Exception {
		int res = sql.selectOne(ns+".OAuthIdChk", userId);
		return res;
	}
	
	@Override
	public int OAuthNameChk(String userName) throws Exception {
		int res = sql.selectOne(ns+".OAuthNameChk", userName);
		return res;
	}
	
	@Override
	public int OAuthEmailChk(String userEmail) throws Exception {
		int res = sql.selectOne(ns+".OAuthEmailChk", userEmail);
		return res;
	}
	
	@Override
	public OAuthUserVO getOAuthUserInfo(String userId) throws Exception {
		return sql.selectOne(ns+".getOAuthUserInfo", userId);
	}
	
	@Override
	public String getOAuthUserAuthority(String userId) throws Exception {
		return sql.selectOne(ns+".getOAuthUserAuthority", userId);
	}
	
	@Override
	public int signoutOAuth(String userId) throws Exception {
		return sql.delete(ns+".signoutOAuth", userId);
	}
	
	@Override
	public int modifyOAuthUser(OAuthUserVO vo) throws Exception {
		return sql.update(ns+".modifyOAuthUser", vo);
	}
	
	@Override
	public int updateOAuthToken(OAuthUserVO vo) throws Exception {
		return sql.update(ns+".updateOAuthToken", vo);
	}
}
