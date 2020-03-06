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
	public void updateStudyGroup() throws Exception {
		dao.deleteDemeritUser();
		dao.deleteStudyGroup();
		dao.updateMemberMaster();
		dao.updateMaster();
		return;
	}
}
