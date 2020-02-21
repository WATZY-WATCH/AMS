package ams.group.domain;

import java.util.Date;

public class GroupScheduleVO {
	private int scheduleId;
	private int groupId;
	private double placeLatitude;
	private double placeLongitude;
	private String placeAddress;
	private String buildingName;
	private Date beginTime;
	private Date endTime;
	private Date regDate;
	private Date modDate;
	
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public double getPlaceLatitude() {
		return placeLatitude;
	}
	public void setPlaceLatitude(double placeLatitude) {
		this.placeLatitude = placeLatitude;
	}
	public double getPlaceLongitude() {
		return placeLongitude;
	}
	public void setPlaceLongitude(double placeLongitude) {
		this.placeLongitude = placeLongitude;
	}
	public String getPlaceAddress() {
		return placeAddress;
	}
	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
