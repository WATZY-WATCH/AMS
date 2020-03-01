package ams.server.dao;

import ams.group.domain.GroupMemberVO;

public interface AuthenticationDAO {

	public int memberChk(GroupMemberVO vo)throws Exception;
	public String authorityChk(GroupMemberVO vo) throws Exception;
}
