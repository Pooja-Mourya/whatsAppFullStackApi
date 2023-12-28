package com.example.payload;

import java.sql.Date;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Size(min = 4, message="name grather then 4 chats !!")
	private String name;
	@NotEmpty
	@Size(min = 3, max = 10 , message="password length should be min 3 and max 10 !!")
	private String password;
	@NotEmpty(message="About field is required")
	private String about;
	private Date createAt;
	private Set<CommentDto> content;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(int id, String email, String name, String password, String about, Date createAt, Set<CommentDto> content) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.about = about;
		this.createAt = createAt;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Set<CommentDto> getContent() {
		return content;
	}
	public void setContent(Set<CommentDto> content) {
		this.content = content;
	}
		
}
