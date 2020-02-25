package ams.group.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ams.group.dao.GroupCommentDAO;
import ams.group.dao.GroupDAO;
import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupCriteria;

@Service
public class GroupCommentServiceImpl implements GroupCommentService {
	
	@Inject GroupCommentDAO dao;
	@Inject GroupDAO gdao;
	
	@Override
	public int createComment(GroupCommentVO vo) throws Exception {
		int ret= dao.createComment(vo);
	    gdao.updateCommentCnt(vo.getGroupId(), 1);
		return ret;
	}
	@Override
	public int updateComment(GroupCommentVO vo) throws Exception {
		return dao.updateComment(vo);
	}
	@Override
	public int deleteComment(GroupCommentVO vo) throws Exception {
		int ret = dao.deleteComment(vo);
		gdao.updateCommentCnt(vo.getGroupId(), -1);
		return ret;
	}
	@Override
	public List<GroupCommentVO> commentList(int groupId, GroupCriteria cri) throws Exception {
		return dao.commentList(groupId, cri);
	}
	@Override
	public int commentCount(int groupId) throws Exception {
		return dao.commentCount(groupId);
	}
}
