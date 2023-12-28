package com.example.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(name = "post_title", length = 100, nullable = false)
	private String title;
	@Column(length = 250, nullable = false)
	private String content;
	private String postImage;
	private Date createdAt;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "user_id") // Use the actual column name for the join
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comments> comment = new HashSet();

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int postId, String title, String content, String postImage, Date createdAt, Category category,
			User user, Set<Comments> comment) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.postImage = postImage;
		this.createdAt = createdAt;
		this.category = category;
		this.user = user;
		this.comment = comment;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comments> getComment() {
		return comment;
	}

	public void setComment(Set<Comments> comment) {
		this.comment = comment;
	}

}
