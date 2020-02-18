package ams.group.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ams.group.dao.GroupDAO;
import ams.group.domain.GroupApplicationsVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;


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
	@Override
	public List<GroupVO> listPage(int page) throws Exception {
		return dao.listPage(page);
	}
	@Override
	public List<GroupVO> listCriteria(GroupCriteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	@Override
	public int countPaging(GroupCriteria cri) throws Exception {
		return dao.countPaging(cri);
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
	public int memberChk(GroupMemberVO vo)throws Exception {
		return dao.memberChk(vo);
	}
	@Override
	public int listApply(GroupApplicationsVO vo)throws Exception {
		return dao.listApply(vo);
	}
	@Override
	public int listApplyChk(GroupApplicationsVO vo) throws Exception {
		return dao.listApplyChk(vo);
	}
}
