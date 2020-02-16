package ams.group.domain;

import java.util.Date;

public class GroupVO {
	private int groupId;
	private String groupCategory;
	private String groupName;
	private String groupDetail;
	private int groupMemberLimit;
	private String groupPeriod;
	private String groupArea;
	private String groupStatus;
	private String groupStartAge;
	private String groupEndAge;
	private Date regDate;
	private Date modDate;
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupCategory() {
		return groupCategory;
	}
	public void setGroupCategory(String groupCategory) {
		this.groupCategory = groupCategory;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDetail() {
		return groupDetail;
	}
	public void setGroupDetail(String groupDetail) {
		this.groupDetail = groupDetail;
	}
	public int getGroupMemberLimit() {
		return groupMemberLimit;
	}
	public void setGroupMemberLimit(int groupMemberLimit) {
		this.groupMemberLimit = groupMemberLimit;
	}
	public String getGroupPeriod() {
		return groupPeriod;
	}
	public void setGroupPeriod(String groupPeriod) {
		this.groupPeriod = groupPeriod;
	}
	public String getGroupArea() {
		return groupArea;
	}
	public void setGroupArea(String groupArea) {
		this.groupArea = groupArea;
	}
	public String getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	public String getGroupStartAge() {
		return groupStartAge;
	}
	public void setGroupStartAge(String groupStartAge) {
		this.groupStartAge = groupStartAge;
	}
	public String getGroupEndAge() {
		return groupEndAge;
	}
	public void setGroupEndAge(String groupEndAge) {
		this.groupEndAge = groupEndAge;
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