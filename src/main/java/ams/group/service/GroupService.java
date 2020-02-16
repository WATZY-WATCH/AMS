package ams.group.service;

import ams.group.domain.GroupMemberVO;
import ams.group.domain.GroupVO;

public interface GroupService {
	public int createGroup(GroupVO vo) throws Exception;
	public int createGroupMember(GroupMemberVO vo) throws Exception;
}
