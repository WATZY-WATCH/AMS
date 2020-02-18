package ams.group.domain;

import java.util.Date;

public class GroupApplicationsVO {
	private int groupId;
	private String userId;
	private String userName;
	private String msg;
	private Date regDate;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "GroupApplicationsVO [groupId=" + groupId + ", userId=" + userId + ", userName=" + userName + ", msg="
				+ msg + ", regDate=" + regDate + "]";
	}
	
}
