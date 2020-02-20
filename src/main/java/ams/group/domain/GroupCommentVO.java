package ams.group.domain;

import java.util.Date;

public class GroupCommentVO {
	private int commentId;
	private int groupId;
	private String userId;
	private String commentMsg;
	private Date regDate;
	private Date modDate;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentMsg() {
		return commentMsg;
	}
	public void setCommentMsg(String commentMsg) {
		this.commentMsg = commentMsg;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	@Override
	public String toString() {
		return "CommentVO [commentId=" + commentId + ", groupId=" + groupId + ", userId=" + userId + ", commentMsg="
				+ commentMsg + ", regDate=" + regDate + ", modDate=" + modDate + "]";
	}
}
