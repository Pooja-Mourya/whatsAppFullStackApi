package com.panel.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String content;
	private LocalDateTime timestamp;
	@ManyToOne
	private User user;

	@ManyToOne
	@JoinColumn(name = "chat_id")
	private MyChat chat;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Integer id, String content, LocalDateTime timestamp, User user, MyChat chat) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
		this.user = user;
		this.chat = chat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MyChat getChat() {
		return chat;
	}

	public void setChat(MyChat chat) {
		this.chat = chat;
	}

}
