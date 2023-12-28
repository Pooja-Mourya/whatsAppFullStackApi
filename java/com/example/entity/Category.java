package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String categoryName;
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category")
	private List<Post> post = new ArrayList<>();
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(Integer id, String categoryName, String categoryDescription, List<Post> post) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.post = post;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}


	
	
}
