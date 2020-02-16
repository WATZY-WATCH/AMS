package ams.group.dao;

import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;

public interface GroupDAO {
	public int createGroup(GroupVO vo) throws Exception;
	public int createGroupMember(GroupMemberVO vo) throws Exception;
}