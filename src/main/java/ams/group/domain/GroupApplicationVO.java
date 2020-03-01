package ams.group.domain;

import java.util.Date;

import ams.user.domain.UserVO;

public class GroupApplicationVO {
	private int applicationId;
	private int groupId;
	private String userId;
	private String msg;
	private Date regDate;
	private UserVO userVO;
	
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
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	@Override
	public String toString() {
		return "GroupApplicationVO [applicationId=" + applicationId + ", groupId=" + groupId + ", userId=" + userId
				+ ", msg=" + msg + ", regDate=" + regDate + ", userVO=" + userVO + "]";
	}
	
}
