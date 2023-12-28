package com.example.payload;

import java.sql.Date;
import java.util.Set;


public class PostDto {

	private String title;
	private String content;
	private String postImage;
	private Date createdAt;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comment;
	public PostDto() {
		super();
	}

	public PostDto(String title, String content, String postImage, Date createdAt, CategoryDto category, UserDto user, Set<CommentDto> comment) {
		super();
		this.title = title;
		this.content = content;
		this.postImage = postImage;
		this.createdAt = createdAt;
		this.category = category;
		this.user = user;
		this.comment = comment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Set<CommentDto> getComment() {
		return comment;
	}

	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}
	
}
