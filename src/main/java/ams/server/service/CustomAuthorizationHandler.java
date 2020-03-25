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
		
		int ret = service.memberChk(vo);
		return ret > 0;
	}
	
	public boolean isAdmin(int groupId, String userId) throws Exception {
		GroupMemberVO vo = new GroupMemberVO();
		vo.setGroupId(groupId);
		vo.setUserId(userId);
		
		String authority = service.authorityChk(vo);

		//authority.equals("MASTER")�� ��� authority�� Null�̸� NullPointerException �߻� 
		return "MASTER".equals(authority);
	}
}
