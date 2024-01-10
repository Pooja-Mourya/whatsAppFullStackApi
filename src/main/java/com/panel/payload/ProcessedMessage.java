package com.panel.payload;

import java.time.LocalDateTime;

import com.panel.entity.MyChat;
import com.panel.entity.User;

public class ProcessedMessage {
    private String content;
    private LocalDateTime timestamp;
    private MyChat chat;
    private User user;
    private Integer id;

    // Constructors, getters, and setters

    public ProcessedMessage() {
		super();
		// TODO Auto-generated constructor stub
	}  

    public ProcessedMessage(String content, LocalDateTime timestamp, MyChat chat, User user, Integer id) {
        this.content = content;
        this.timestamp = timestamp;
        this.chat = chat;
        this.user = user;
        this.id = id;
    }
    // Getters and Setters

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public MyChat getChat() {
        return chat;
    }

    public void setChat(MyChat chat) {
        this.chat = chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
