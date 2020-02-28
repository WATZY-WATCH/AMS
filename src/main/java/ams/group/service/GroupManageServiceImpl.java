package ams.group.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.group.dao.GroupManageDAO;
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
}
