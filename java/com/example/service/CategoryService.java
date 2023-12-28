package com.example.service;

import java.util.List;

import com.example.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto catDto);
	
	CategoryDto updateCategory(CategoryDto catDto, Integer catId);
	
	CategoryDto byIdCategory(Integer catId);
	
	List<CategoryDto> allCategory();
	
	void deleteCategory(Integer catId);
}
