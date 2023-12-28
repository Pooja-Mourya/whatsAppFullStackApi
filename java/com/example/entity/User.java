package com.example.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String name;
	private String password;
	private String about;
	private Date createAt;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> post = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Comments> comment = new HashSet();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String email, String name, String password, String about, Date createAt, List<Post> post,
			Set<Comments> comment) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.about = about;
		this.createAt = createAt;
		this.post = post;
		this.comment = comment;
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

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public Set<Comments> getComment() {
		return comment;
	}

	public void setComment(Set<Comments> comment) {
		this.comment = comment;
	}

}
