package com.panel.request;

import java.util.List;

public class GroupChatRequest {

	private List<Integer>userId;
	private String chatName;
	private String chatImage;
	
	public GroupChatRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupChatRequest(List<Integer> userId, String chatName, String chatImage) {
		super();
		this.userId = userId;
		this.chatName = chatName;
		this.chatImage = chatImage;
	}

	public List<Integer> getUserId() {
		return userId;
	}

	public void setUserId(List<Integer> userId) {
		this.userId = userId;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public String getChatImage() {
		return chatImage;
	}

	public void setChatImage(String chatImage) {
		this.chatImage = chatImage;
	}
	
	
}
