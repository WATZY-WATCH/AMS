package ams.user.domain;

import java.util.Date;

public class UserVO {
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private Date regDate;
	private Date modDate;
	private boolean enabled;
	
	private String weekStart;
	private String weekEnd;
	
	public String getWeekStart() {
		return weekStart;
	}
	public void setWeekStart(String weekStart) {
		this.weekStart = weekStart;
	}
	public String getWeekEnd() {
		return weekEnd;
	}
	public void setWeekEnd(String weekEnd) {
		this.weekEnd = weekEnd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw + "]";
	}
}
