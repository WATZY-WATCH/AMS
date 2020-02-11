package ams.user.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.user.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Inject SqlSession sql;
	
	private static final String ns = "ams.user.mapper.userMapper";
	
	@Override
	public void signup(UserVO vo) throws Exception {
		sql.insert(ns+".signup", vo);
		sql.insert(ns+".getAuthority", vo);
	}
	
	@Override
	public int idChk(String userId) throws Exception {
		int res = sql.selectOne(ns+".idChk", userId);
		return res;
	}
	
	@Override
	public int nameChk(String userName) throws Exception {
		int res = sql.selectOne(ns+".nameChk", userName);
		return res;
	}
	
	@Override
	public int emailChk(String userEmail) throws Exception {
		int res = sql.selectOne(ns+".emailChk", userEmail);
		return res;
	}
	
	@Override
	public int modifyUser(UserVO vo) throws Exception {
		return sql.update(ns+".modifyUser", vo);
	}
	
	@Override
	public UserVO getUserInfo(String userId) throws Exception {
		return sql.selectOne(ns+".getUserInfo",userId);
	}
	
	@Override
	public String findUser(String userId) throws Exception{
		return sql.selectOne(ns+".findUser",userId);
	}
}
