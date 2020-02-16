package ams.group.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;

@Repository
public class GroupDAOImpl implements GroupDAO{
	@Inject SqlSession sql;
	
	private static final String ns = "ams.group.mapper.groupMapper";
	
	@Override
	public int createGroup(GroupVO vo) throws Exception {
		return sql.insert(ns+".groupCreate", vo);
	}
	@Override
	public int createGroupMember(GroupMemberVO vo) throws Exception {
		return sql.insert(ns+".groupCreateMember", vo);
	}
}
