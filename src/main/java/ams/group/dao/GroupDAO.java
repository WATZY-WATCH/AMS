package ams.group.dao;

import ams.group.domain.GroupVO;

public interface GroupDAO {
	public int createGroup(GroupVO vo) throws Exception;
}