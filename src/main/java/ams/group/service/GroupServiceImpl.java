package ams.group.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.group.dao.GroupDAO;
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
}
