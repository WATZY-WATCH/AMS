package ams.server.service;

import ams.group.domain.GroupMemberVO;

public interface AuthenticationService {
	public int memberChk(GroupMemberVO vo)throws Exception;
	public String authorityChk(GroupMemberVO vo) throws Exception;
}
