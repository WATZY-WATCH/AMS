package ams.group.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupApplicationsVO;
import ams.group.domain.GroupAttendanceVO;
import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupScheduleVO;
import ams.group.domain.GroupVO;
import ams.group.domain.SearchCriteria;

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
	public GroupVO listRead(int groupId) throws Exception {
		return sql.selectOne(ns+".listRead", groupId);
	}
	@Override
	public void updateViewCnt(int groupId) throws Exception {
		sql.update(ns+".updateViewCnt", groupId);
		return;
	}
	@Override
	public int memberChk(GroupMemberVO vo) throws Exception {
		return sql.selectOne(ns+".memberChk",vo);
	}
	@Override
	public int listApply(GroupApplicationsVO vo) throws Exception {
		return sql.insert(ns+".listApply",vo);
	}
	@Override
	public int listApplyChk(GroupApplicationsVO vo) throws Exception {
		return sql.selectOne(ns+".listApplyChk",vo);
	}
	@Override
	public List<GroupVO> listSearch(SearchCriteria cri) throws Exception {
		return sql.selectList(ns+".listSearch",cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return sql.selectOne(ns+".listSearchCount",cri);
	}
	@Override
	public List<GroupCommentVO> commentList(int groupId) throws Exception {
		return sql.selectList(ns+".commentList", groupId);
	}
	@Override
	public int createComment(GroupCommentVO vo) throws Exception {
		return sql.insert(ns+".commentCreate", vo);
	}
	@Override
	public int updateComment(GroupCommentVO vo) throws Exception {
		return sql.update(ns+".commentUpdate", vo);
	}
	@Override
	public int deleteComment(int commentId) throws Exception {
		return sql.delete(ns+".deleteComment", commentId);
	}
	@Override
	public int createSchedule(GroupScheduleVO vo) throws Exception {
		return sql.insert(ns+".createSchedule", vo);
	}
	@Override
	public int modifySchedule(GroupScheduleVO vo) throws Exception {
		return sql.update(ns+".modifySchedule", vo);
	}
	@Override
	public int deleteSchedule(int scheduleId) throws Exception {
		return sql.delete(ns+".deleteSchedule", scheduleId);
	}
	@Override
	public List<GroupScheduleVO> getScheduleList(int groupId) throws Exception {
		return sql.selectList(ns+".getScheduleList", groupId);
	}
	@Override
	public GroupScheduleVO getSchedule(GroupScheduleVO vo) throws Exception {
		return sql.selectOne(ns+".getSchedule", vo);
	}
	@Override
	public int requestAttend(GroupAttendanceVO vo) throws Exception {
		return sql.insert(ns+".requestAttend", vo);
	}
	@Override
	public String chkAttendanceStatus(GroupAttendanceVO vo) throws Exception {
		return sql.selectOne(ns+".chkAttend", vo);
	}
}
