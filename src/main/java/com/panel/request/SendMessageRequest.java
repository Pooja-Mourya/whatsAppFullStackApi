package com.panel.request;

public class SendMessageRequest {

	private int userId;
	private int chatId;
	private String content;
	
	public int getUserId() {
		return userId;
	}
	
	public SendMessageRequest(int userId, int chatId, String content) {
		super();
		this.userId = userId;
		this.chatId = chatId;
		this.content = content;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
