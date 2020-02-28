package ams.group.domain;

import java.util.Arrays;
import java.util.List;

public class SearchCriteria extends GroupCriteria{
	private String searchType;
	private String keyword;
	private List<String> category;
	private List<String> area;
	private int startAge;
	private int endAge;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	
	public List<String> getArea() {
		return area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	
	public int getStartAge() {
		return startAge;
	}
	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}
	public int getEndAge() {
		return endAge;
	}
	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}
	@Override
	public String toString() {
		return super.toString()+"SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + ", category=" + category
				+ ", area=" + area + ", startAge=" + startAge + ", endAge=" + endAge + "]";
	}
}
