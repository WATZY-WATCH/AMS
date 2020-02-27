package ams.server.service;

import ams.group.domain.GroupMemberVO;

public interface AuthenticationService {
	public int memberChk(GroupMemberVO vo)throws Exception;
}
