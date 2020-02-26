package ams.server.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.group.domain.GroupAttendanceVO;
import ams.server.dao.SchedulingDAO;

@Service
public class SchedulingServiceImpl implements SchedulingService {
	@Inject SchedulingDAO dao;
	
	@Override
	public List<GroupAttendanceVO> getAbsentList() throws Exception {
		return dao.getAbsentList();
	}
	@Override
	public int insertAbsentList(List<GroupAttendanceVO> list) throws Exception {
		return dao.insertAbsentList(list);
	}
	@Override
	public int addDemerit(List<GroupAttendanceVO> list) throws Exception {
		return dao.addDemerit(list);
	}
	@Override
	public int deleteDemeritUser() throws Exception {
		return dao.deleteDemeritUser();
	}
}
