package ams.server.service;

import java.util.List;

import ams.group.domain.GroupAttendanceVO;

public interface SchedulingService {
	public List<GroupAttendanceVO> getAbsentList() throws Exception;
	public int insertAbsentList(List<GroupAttendanceVO> list) throws Exception;
}
