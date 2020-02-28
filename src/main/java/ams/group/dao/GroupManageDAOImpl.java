package ams.group.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupVO;

@Repository
public class GroupManageDAOImpl implements GroupManageDAO {
	@Inject SqlSession sql;
	
	private static final String ns = "ams.group.mapper.groupManageMapper";
	
	@Override
	public List<GroupVO> masterList(String userId)throws Exception{
		return sql.selectList(ns+".masterList",userId);
	}
	@Override
	public List<GroupVO> memberList(String userId)throws Exception{
		return sql.selectList(ns+".memberList",userId);
	}
	@Override
	public List<GroupVO> applicationList(String userId)throws Exception{
		return sql.selectList(ns+".applicationList",userId);
	}
}
