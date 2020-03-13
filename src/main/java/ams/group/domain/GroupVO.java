package ams.group.domain;

import java.util.Date;

import ams.user.domain.UserVO;

public class GroupVO {
	
	private int groupId;
	private String groupCategory;
	private String groupMasterId;
	private String groupName;
	private String groupDetail;
	private int groupMemberCnt;
	private int groupMemberLimit;
	private String groupPeriod;
	private String groupArea;
	private String groupStatus;
	private String groupStartAge;
	private String groupEndAge;
	private Date regDate;
	private Date modDate;
	private int groupViewCnt;
	private int groupCommentCnt;
	private UserVO userVO;
	private GroupMemberVO groupMemberVO;
	
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
	public int getGroupViewCnt() {
		return groupViewCnt;
	}
	public void setGroupViewCnt(int groupViewCnt) {
		this.groupViewCnt = groupViewCnt;
	}
	public String getGroupMasterId() {
		return groupMasterId;
	}
	public void setGroupMasterId(String groupMasterId) {
		this.groupMasterId = groupMasterId;
	}
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	public int getGroupCommentCnt() {
		return groupCommentCnt;
	}
	public void setGroupCommentCnt(int groupCommentCnt) {
		this.groupCommentCnt = groupCommentCnt;
	}
	public GroupMemberVO getGroupMemberVO() {
		return groupMemberVO;
	}
	public void setGroupMemberVO(GroupMemberVO groupMemberVO) {
		this.groupMemberVO = groupMemberVO;
	}
	public int getGroupMemberCnt() {
		return groupMemberCnt;
	}
	public void setGroupMemberCnt(int groupMemberCnt) {
		this.groupMemberCnt = groupMemberCnt;
	}
	
	
}