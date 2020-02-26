package ams.group.service;

import java.util.List;

import ams.group.domain.GroupApplicationsVO;
import ams.group.domain.GroupAttendanceVO;
import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupScheduleVO;
import ams.group.domain.GroupVO;
import ams.group.domain.SearchCriteria;

public interface GroupService {
	public int createGroup(GroupVO vo) throws Exception;
	public int createGroupMember(GroupMemberVO vo) throws Exception;
	public GroupVO listRead(int groupId)throws Exception;
	public void updateViewCnt(int groupId)throws Exception;
	public int memberChk(GroupMemberVO vo)throws Exception;
	public int listApply(GroupApplicationsVO vo)throws Exception;
	public int listApplyChk(GroupApplicationsVO vo)throws Exception;
	public List<GroupVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;
	public List<GroupCommentVO> commentList(int groupId)throws Exception;
	public int createComment(GroupCommentVO vo)throws Exception;
	public int updateComment(GroupCommentVO vo)throws Exception;
	public int deleteComment(int commentId)throws Exception;
	public int createSchedule(GroupScheduleVO vo) throws Exception;
	public int modifySchedule(GroupScheduleVO vo) throws Exception;
	public int deleteSchedule(int scheduleId) throws Exception;
	public List<GroupScheduleVO> getScheduleList(int groupId) throws Exception;
	public GroupScheduleVO getSchedule(GroupScheduleVO vo) throws Exception;
	public int requestAttend(GroupAttendanceVO vo) throws Exception;
	public String chkAttendanceStatus(GroupAttendanceVO vo) throws Exception;
}
