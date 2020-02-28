package ams.server.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.group.domain.GroupMemberVO;
import ams.server.dao.AuthenticationDAO;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Inject AuthenticationDAO dao;
	
	@Override
	public int memberChk(GroupMemberVO vo) throws Exception {
		return dao.memberChk(vo);
	}
	@Override
	public String authorityChk(GroupMemberVO vo) throws Exception {
		return dao.authorityChk(vo);
	}
}
