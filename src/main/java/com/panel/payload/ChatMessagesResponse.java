package com.panel.payload;

import java.util.List;

import com.panel.entity.Message;

public class ChatMessagesResponse {
    private List<Message> messages;
    private String chatId;
	public ChatMessagesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatMessagesResponse(List<Message> messages, String chatId) {
		super();
		this.messages = messages;
		this.chatId = chatId;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

    
}

