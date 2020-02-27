package ams.server.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupMemberVO;

@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO {
	
	@Inject SqlSession sql;

	private static final String ns = "ams.server.mapper.serverMapper";
	
	@Override
	public int memberChk(GroupMemberVO vo) throws Exception {
		return sql.selectOne(ns+".memberChk",vo);
	}
}
