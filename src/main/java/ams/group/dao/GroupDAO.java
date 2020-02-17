package ams.group.dao;

import java.util.List;

import ams.group.domain.GroupCriteria;
import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;

public interface GroupDAO {
	public int createGroup(GroupVO vo) throws Exception;
	public int createGroupMember(GroupMemberVO vo) throws Exception;
	public List<GroupVO> listPage(int page)throws Exception;
	public List<GroupVO> listCriteria(GroupCriteria cri)throws Exception;
	public int countPaging(GroupCriteria cri)throws Exception;
	public GroupVO listRead(int groupId)throws Exception;
}