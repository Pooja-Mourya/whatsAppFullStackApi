package com.panel.payload;

import java.util.List;

import com.panel.entity.User;


public class UserReponse {

	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private boolean lastPage;
	private List<User> content;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public List<User> getContent() {
		return content;
	}
	public void setContent(List<User> dtos) {
		this.content = dtos;
	}
	
	
}
