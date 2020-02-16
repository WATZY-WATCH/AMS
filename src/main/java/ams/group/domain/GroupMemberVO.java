package ams.group.domain;

import java.util.Date;

public class GroupMemberVO {
	private int groupId;
	private String userId;
	private String groupAuthority;
	private int demeritCnt;
	private Date regDate;
	private Date modDate;
	
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
	public String getGroupAuthority() {
		return groupAuthority;
	}
	public void setGroupAuthority(String groupAuthority) {
		this.groupAuthority = groupAuthority;
	}
	public int getDemeritCnt() {
		return demeritCnt;
	}
	public void setDemeritCnt(int demeritCnt) {
		this.demeritCnt = demeritCnt;
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
