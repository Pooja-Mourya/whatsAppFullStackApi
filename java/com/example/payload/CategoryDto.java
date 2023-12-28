package com.example.payload;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {

	private Integer id;
	@NotEmpty
	private String categoryName;
	@NotEmpty
	private String categoryDescription;
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryDto(Integer id, String categoryName, String categoryDescription) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
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
	
	
}
