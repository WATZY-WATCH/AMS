package ams.server.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import ams.group.domain.GroupMemberVO;

@Component
public class CustomAuthorizationHandler {
	
	@Inject AuthenticationService service;
	
	public boolean isMember(int groupId, String userId) throws Exception {
		GroupMemberVO vo = new GroupMemberVO();
		vo.setGroupId(groupId);
		vo.setUserId(userId);
		
		System.out.println(groupId + " " +  userId);
		
		int ret = service.memberChk(vo);
		
		System.out.println(ret);
		return ret > 0;
	}
	
	public boolean isAdmin(int groupId, String userId) throws Exception {
		System.out.println("admin인지 확인 ");
		GroupMemberVO vo = new GroupMemberVO();
		vo.setGroupId(groupId);
		vo.setUserId(userId);
		
		System.out.println(groupId + " " +  userId);
		
		String authority = service.authorityChk(vo);
		System.out.println(authority);
		
		//authority.equals("MASTER")의 경우 authority가 Null이면 NullPointerException 발생 
		return "MASTER".equals(authority);
	}
}
