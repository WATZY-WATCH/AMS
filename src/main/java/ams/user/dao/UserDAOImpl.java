package ams.user.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupScheduleVO;
import ams.group.domain.GroupVO;
import ams.user.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Inject SqlSession sql;
	
	private static final String ns = "ams.user.mapper.userMapper";
	
	@Override
	public void signup(UserVO vo) throws Exception {
		sql.insert(ns+".signup", vo);
	}
	
	@Override
	public void signupOAuth(UserVO vo) throws Exception {
		sql.insert(ns+".signupOAuth", vo);
	}
	
	@Override
	public void setAuthority(UserVO vo) throws Exception {
		sql.insert(ns+".setAuthority", vo);
	}
	
	@Override
	public String getAuthority(String userId) throws Exception {
		return sql.selectOne(ns+".getAuthority", userId);
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
	public int pwChk(UserVO vo) throws Exception {
		
		return sql.selectOne(ns+".pwChk", vo);
	}
	
	@Override
	public int signout(String userId) throws Exception {
		return sql.delete(ns+".signout", userId);
	}
	
	@Override
	public int modifyUser(UserVO vo) throws Exception {
		return sql.update(ns+".modifyUser", vo);
	}
	
	@Override
	public int modifyUserPw(UserVO vo) throws Exception {
		return sql.update(ns+".modifyUserPw", vo);
	}
	
	@Override
	public int updateOAuthToken(UserVO vo) throws Exception {
		return sql.update(ns+".updateOAuthToken", vo);
	}
	
	@Override
	public UserVO getUserInfo(String userId) throws Exception {
		return sql.selectOne(ns+".getUserInfo",userId);
	}
	
	@Override
	public String findUser(String userId) throws Exception{
		return sql.selectOne(ns+".findUser",userId);
	}

	@Override
	public List<GroupVO> findJoinedGroup(String userId) throws Exception {
		return sql.selectList(ns+".findJoinedGroup", userId);
	}
	
	@Override
	public List<GroupScheduleVO> showWeekSchedule(UserVO vo) throws Exception {
		return sql.selectList(ns+".showWeekSchedule", vo);
	}
	
	@Override 
	public int signoutComment(String userId) throws Exception{
		return sql.update(ns+".signoutComment", userId);
	}
	
	@Override
	public int signoutStudyGroup(String userId) throws Exception{
		return sql.delete(ns+".signoutStudyGroup", userId);
	}
	
	@Override
	public int updateMemberMaster() throws Exception {
		return sql.update(ns+".updateMemberMaster");
	}
	@Override
	public int updateMaster() throws Exception {
		return sql.update(ns+".updateMaster");
	}
}
