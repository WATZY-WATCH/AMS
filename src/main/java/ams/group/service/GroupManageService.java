package ams.group.service;

import java.util.List;

import ams.group.domain.GroupVO;

public interface GroupManageService {
	public List<GroupVO> masterList(String userId)throws Exception;
	public List<GroupVO> memberList(String userId)throws Exception;
	public List<GroupVO> applicationList(String userId)throws Exception;
}
