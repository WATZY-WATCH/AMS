package ams.group.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupCriteria;
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
	@Override
	public List<GroupVO> listPage(int page) throws Exception {
		if(page<=0) page =1;
		page=(page-1)*10;
		return sql.selectList(ns+".listPage",page);
	}
	@Override
	public List<GroupVO> listCriteria(GroupCriteria cri) throws Exception {
		return sql.selectList(ns+".listCriteria", cri);
	}
	@Override
	public int countPaging(GroupCriteria cri) throws Exception {
		return sql.selectOne(ns+".countPaging", cri);
	}
	@Override
	public GroupVO listRead(int groupId) throws Exception {
		return sql.selectOne(ns+".listRead", groupId);
	}
}
