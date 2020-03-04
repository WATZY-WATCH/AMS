package ams.group.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.group.dao.GroupManageDAO;
import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;

@Service
public class GroupManageServiceImpl implements GroupManageService{
	
	@Inject GroupManageDAO dao;
	
	@Override
	public List<GroupVO> masterList(String userId)throws Exception{
		return dao.masterList(userId);
	}
	@Override
	public List<GroupVO> memberList(String userId)throws Exception{
		return dao.memberList(userId);
	}
	@Override
	public List<GroupVO> applicationList(String userId)throws Exception{
		return dao.applicationList(userId);
	}
	@Override
	public List<GroupApplicationVO> masterApplicationList(int groupId, GroupCriteria cri)throws Exception{
		return dao.masterApplicationList(groupId, cri);
	}
	@Override
	public int masterApplicationCount(int groupId)throws Exception{
		return dao.masterApplicationCount(groupId);
	}
	@Override
	public GroupApplicationVO masterApplicationRead(int applicationId)throws Exception{
		return dao.masterApplicationRead(applicationId);
	}
	@Override
	public int masterAccept(GroupApplicationVO vo)throws Exception{
		return dao.masterAccept(vo);
	}
	@Override
	public int applicationDelete(int applicationId)throws Exception{
		return dao.applicationDelete(applicationId);
	}
	@Override
	public GroupMemberVO selectMember(int groupId, String userId)throws Exception{
		return dao.selectMember(groupId, userId);
	}
	@Override
	public GroupVO selectGroup(int groupId)throws Exception{
		return dao.selectGroup(groupId);
	}
	@Override
	public int deleteMember(GroupMemberVO vo)throws Exception{
		return dao.deleteMember(vo);
	}
	@Override
	public int deleteApplication(GroupApplicationVO vo)throws Exception{
		return dao.deleteApplication(vo);
	}
	@Override
	public GroupApplicationVO seleteApplication(int groupId, String userId)throws Exception{
		return dao.seleteApplication(groupId, userId);
	}
	@Override
	public int masterDelete(GroupMemberVO vo)throws Exception{
		int ret=dao.nextMasterCheck(vo.getGroupId());
		if(ret>0) {
			dao.updateMaster(vo.getGroupId());
			dao.deleteMember(vo);
			return dao.updateGroup(vo.getGroupId());
		}else {
			dao.deleteMember(vo);
			return dao.deleteGroup(vo.getGroupId());
		}
	}

}
