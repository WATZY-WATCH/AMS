package ams.server.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ams.group.domain.GroupAttendanceVO;
import ams.server.dao.SchedulingDAO;

@Service
public class SchedulingServiceImpl implements SchedulingService {
	@Inject SchedulingDAO dao;
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public void updateAbsent() throws Exception {
		List<GroupAttendanceVO> list = dao.getAbsentList();
		dao.insertAbsentList(list);
		dao.addDemerit(list);
		return;
	}
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
<<<<<<< HEAD
	public void updateStudyGroup() throws Exception {
		dao.deleteDemeritUser();
		dao.deleteStudyGroup();
		dao.updateMaster();
		return;
=======
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
>>>>>>> 7527d3fc6a7dfc9a7ce0cf124483374344a530f7
	}
}
