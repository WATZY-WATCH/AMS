package ams.group.service;

import java.util.List;

import ams.group.domain.GroupApplicationVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupVO;

public interface GroupManageService {
	public List<GroupVO> masterList(String userId)throws Exception;
	public List<GroupVO> memberList(String userId)throws Exception;
	public List<GroupVO> applicationList(String userId)throws Exception;
	public List<GroupApplicationVO> masterApplicationList(int groupId, GroupCriteria cri)throws Exception;
	public int masterApplicationCount(int groupId)throws Exception;
	public GroupApplicationVO masterApplicationRead(int applicationId)throws Exception;
	public int masterAccept(GroupApplicationVO vo)throws Exception;
	public int applicationDelete(int applicationId)throws Exception;

}
