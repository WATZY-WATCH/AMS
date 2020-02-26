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
}
