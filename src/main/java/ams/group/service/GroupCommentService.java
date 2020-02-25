package ams.group.service;

import java.util.List;


import ams.group.domain.GroupCommentVO;
import ams.group.domain.GroupCriteria;

public interface GroupCommentService {
		public int createComment(GroupCommentVO vo)throws Exception;
		public int updateComment(GroupCommentVO vo)throws Exception;
		public int deleteComment(GroupCommentVO vo)throws Exception;
		public List<GroupCommentVO> commentList(int groupId, GroupCriteria cri)throws Exception;
		public int commentCount(int groupId)throws Exception;
}
