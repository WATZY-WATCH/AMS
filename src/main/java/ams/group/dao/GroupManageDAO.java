package ams.group.dao;

import java.util.List;
import ams.group.domain.GroupVO;

public interface GroupManageDAO {
	public List<GroupVO> masterList(String userId)throws Exception;
	public List<GroupVO> memberList(String userId)throws Exception;
	public List<GroupVO> applicationList(String userId)throws Exception;
}
