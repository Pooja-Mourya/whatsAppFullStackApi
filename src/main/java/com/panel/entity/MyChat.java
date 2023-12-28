package com.panel.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "my_chat")

public class MyChat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private boolean isgroup;
	@JoinColumn(name = "created_by")
	@ManyToOne
	private User created_by;
	@ManyToMany
	private Set<User> admin = new HashSet<>();
	@ManyToMany
	private Set<User> users = new HashSet<>();
	@OneToMany
	private List<Message> messages = new ArrayList<>();
	private String chat_image;

	public MyChat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyChat(int id, String name, boolean isgroup, User created_by, Set<User> admin, Set<User> users,
			List<Message> messages, String chat_image) {
		super();
		this.id = id;
		this.name = name;
		this.isgroup = isgroup;
		this.created_by = created_by;
		this.admin = admin;
		this.users = users;
		this.messages = messages;
		this.chat_image = chat_image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsgroup() {
		return isgroup;
	}

	public void setIsgroup(boolean isgroup) {
		this.isgroup = isgroup;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getChat_image() {
		return chat_image;
	}

	public void setChat_image(String chat_image) {
		this.chat_image = chat_image;
	}

	public Set<User> getAdmin() {
		return admin;
	}

	public void setAdmin(Set<User> admin) {
		this.admin = admin;
	}

}
