package ams.group.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupCriteria;

@Repository
public class GroupCommentDAOImpl implements GroupCommentDAO {
	
	@Inject SqlSession sql;
	
	private static final String ns = "ams.group.mapper.groupCommentMapper";
	
	@Override
	public int createComment(GroupCommentVO vo) throws Exception {
		return sql.insert(ns+".commentCreate", vo);
	}
	@Override
	public int updateComment(GroupCommentVO vo) throws Exception {
		return sql.update(ns+".commentUpdate", vo);
	}
	@Override
	public int deleteComment(GroupCommentVO vo) throws Exception {
		return sql.delete(ns+".commentDelete", vo);
	}
	@Override
	public List<GroupCommentVO> commentList(int groupId, GroupCriteria cri) throws Exception {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("groupId", groupId);
		paramMap.put("cri", cri);
		return sql.selectList(ns+".commentList",paramMap);
	}
	@Override
	public int commentCount(int groupId) throws Exception {
		return sql.selectOne(ns+".commentCount", groupId);
	}
	@Override
	public int currentCommentCount(GroupCommentVO vo)throws Exception{
		return sql.selectOne(ns+".currentCommentCount", vo);
	}
}
