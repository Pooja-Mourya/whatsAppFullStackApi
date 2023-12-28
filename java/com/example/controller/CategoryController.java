package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payload.CategoryDto;
import com.example.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService catService;

	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catDto) {
		CategoryDto createCategory = catService.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto catDto , @PathVariable("catId") Integer catId) {
		CategoryDto updateCategory = catService.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.CREATED);
	}

	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> byIdCategory(@PathVariable Integer catId) {
		CategoryDto byIdCategory = catService.byIdCategory(catId);
		return new ResponseEntity<CategoryDto>(byIdCategory, HttpStatus.ACCEPTED);
	}
	@GetMapping
	public ResponseEntity<List<CategoryDto>> category() {
		List<CategoryDto> category = catService.allCategory();
		return new ResponseEntity<List<CategoryDto>>(category, HttpStatus.ACCEPTED);
	}
}
