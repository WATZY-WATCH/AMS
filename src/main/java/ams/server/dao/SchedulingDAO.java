package ams.server.dao;

import java.util.List;

import ams.group.domain.GroupAttendanceVO;

public interface SchedulingDAO {
	public List<GroupAttendanceVO> getAbsentList() throws Exception;
	public int insertAbsentList(List<GroupAttendanceVO> list) throws Exception;
	public int addDemerit(List<GroupAttendanceVO> list) throws Exception;
	public int deleteDemeritUser() throws Exception;
}
