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
}
