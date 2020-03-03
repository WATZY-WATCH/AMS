package ams.group.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
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
	@Override
	public List<GroupApplicationVO> masterApplicationList(int groupId, GroupCriteria cri)throws Exception{
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("groupId", groupId);
		paramMap.put("cri", cri);
		return  sql.selectList(ns+".masterApplicationList", paramMap);
	}
	@Override
	public int masterApplicationCount(int groupId)throws Exception{
		return  sql.selectOne(ns+".masterApplicationCount", groupId);
	}
	@Override
	public GroupApplicationVO masterApplicationRead(int applicationId)throws Exception{
		return sql.selectOne(ns+".masterApplicationRead", applicationId);
	}
	@Override
	public int masterAccept(GroupApplicationVO vo)throws Exception{
		return sql.insert(ns+".masterAccept",vo);
	}
	@Override
	public int applicationDelete(int applicationId)throws Exception{
		return sql.delete(ns+".applicationDelete", applicationId);
	}
	@Override
	public GroupMemberVO selectMember(int groupId, String userId)throws Exception{
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("groupId", groupId);
		paramMap.put("userId", userId);
		return sql.selectOne(ns+".selectMember", paramMap);
	}
	@Override
	public GroupVO selectGroup(int groupId)throws Exception{
		return sql.selectOne(ns+".selectGroup", groupId);
	}
}
