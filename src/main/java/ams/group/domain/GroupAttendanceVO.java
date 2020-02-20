package ams.group.domain;

import java.util.Date;

public class GroupAttendanceVO {
	private int groupId;
	private int scheduleId;
	private String userId;
	private String attendaceStatus;
	private Date regDate;
	private Date modDate;
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAttendaceStatus() {
		return attendaceStatus;
	}
	public void setAttendaceStatus(String attendaceStatus) {
		this.attendaceStatus = attendaceStatus;
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
}
