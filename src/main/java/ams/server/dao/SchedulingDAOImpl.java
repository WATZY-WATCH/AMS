package ams.server.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ams.group.domain.GroupAttendanceVO;

@Repository
public class SchedulingDAOImpl implements SchedulingDAO {
	
	@Inject SqlSession sql;
	
	private static final String ns = "ams.server.mapper.serverMapper";
	
	@Override
	public List<GroupAttendanceVO> getAbsentList() throws Exception {
		return sql.selectList(ns+".getAbsentList");
	}
	@Override
	public int insertAbsentList(List<GroupAttendanceVO> list) throws Exception {
		return sql.insert(ns+".insertAbsentList", list);
	}
	@Override
	public int addDemerit(List<GroupAttendanceVO> list) throws Exception {
		return sql.update(ns+".addDemerit", list);
	}
	@Override
	public int updateMemberMaster() throws Exception {
		return sql.update(ns+".updateMemberMaster");
	}
	@Override
	public int updateMaster() throws Exception {
		return sql.update(ns+".updateMaster");
	}
	@Override
	public int deleteDemeritUser() throws Exception {
		return sql.delete(ns+".deleteDemeritUser");
	}
	@Override
	public int deleteStudyGroup() throws Exception {
		return sql.delete(ns+".deleteStudyGroup");
	}
}
