package ams.group.service;

import java.util.List;

import ams.group.domain.GroupApplicationsVO;
import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
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
}
