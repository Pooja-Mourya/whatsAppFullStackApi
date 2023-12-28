package com.example.payload;

import java.util.Set;

import com.example.entity.User;

public class CommentDto {
	private Integer id;
	private String content;
	private Set<PostDto> post;
	private User user;

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(Integer id, String content, Set<PostDto> post, User user) {
		super();
		this.id = id;
		this.content = content;
		this.post = post;
		this.user = user;
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

	public Set<PostDto> getPost() {
		return post;
	}

	public void setPost(Set<PostDto> post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
