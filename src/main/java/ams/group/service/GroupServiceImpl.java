package ams.group.service;

import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ams.group.dao.GroupDAO;
import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupAttendanceVO;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupScheduleVO;
import ams.group.domain.GroupVO;
import ams.group.domain.SearchCriteria;


@Service
public class GroupServiceImpl implements GroupService {
	@Inject GroupDAO dao;

	@Override
	public int createGroup(GroupVO vo) throws Exception {
		return dao.createGroup(vo);
	}
	
	@Override
	public int createGroupMember(GroupMemberVO vo) throws Exception {
		return dao.createGroupMember(vo);
	}
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public GroupVO listRead(int groupId)throws Exception {
		dao.updateViewCnt(groupId);
		return dao.listRead(groupId);
	}
	@Override
	public void updateViewCnt(int groupId)throws Exception {
		dao.updateViewCnt(groupId);
		return;
	}
	@Override
	public int memberChk(GroupMemberVO vo) throws Exception {
		return dao.memberChk(vo);
	}
	@Override
	public int listApply(GroupApplicationVO vo)throws Exception {
		return dao.listApply(vo);
	}
	@Override
	public int listApplyChk(GroupApplicationVO vo) throws Exception {
		return dao.listApplyChk(vo);
	}
	@Override
	public List<GroupVO> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}
	@Override
	public int createSchedule(GroupScheduleVO vo) throws Exception {
		return dao.createSchedule(vo);
	}
	@Override
	public int modifySchedule(GroupScheduleVO vo) throws Exception {
		return dao.modifySchedule(vo);
	}
	@Override
	public int deleteSchedule(int scheduleId) throws Exception {
		return dao.deleteSchedule(scheduleId);
	}
	@Override
	public List<GroupScheduleVO> getScheduleList(int groupId) throws Exception {
		return dao.getScheduleList(groupId);
	}
	@Override
	public GroupScheduleVO getSchedule(GroupScheduleVO vo) throws Exception {
		return dao.getSchedule(vo);
	}
	@Override
	public int requestAttend(GroupAttendanceVO vo) throws Exception {
		return dao.requestAttend(vo);
	}
	@Override
	public int addDemerit(GroupMemberVO vo) throws Exception {
		return dao.addDemerit(vo);
	}
	@Override
	public String chkAttendanceStatus(GroupAttendanceVO vo) throws Exception {
		return dao.chkAttendanceStatus(vo);
	}
}
