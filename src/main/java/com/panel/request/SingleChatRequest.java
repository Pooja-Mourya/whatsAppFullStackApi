package com.panel.request;

public class SingleChatRequest {
	private Integer userid;
	public SingleChatRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SingleChatRequest(Integer userid) {
		super();
		this.userid = userid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
}
