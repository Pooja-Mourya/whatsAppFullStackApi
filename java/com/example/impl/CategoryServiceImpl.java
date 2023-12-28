package com.example.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.exceptionHandler.ResouseNotFoundException;
import com.example.payload.CategoryDto;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private CategoryRepository catRepo;

	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		Category map = modelmapper.map(catDto, Category.class);
		Category save = catRepo.save(map);
		CategoryDto categoryDto = modelmapper.map(save, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto catDto, Integer catId) {
		Category map = modelmapper.map(catDto, Category.class);
		catRepo.findById(catId).orElseThrow(() -> new ResouseNotFoundException("Category", "id", null));
		map.setCategoryName(catDto.getCategoryName());
		map.setCategoryDescription(catDto.getCategoryDescription());
		CategoryDto mapDto = modelmapper.map(map, CategoryDto.class);
		return mapDto;
	}

	@Override
	public CategoryDto byIdCategory(Integer catId) {
		Category category = catRepo.findById(catId)
				.orElseThrow(() -> new ResouseNotFoundException("category not found : ", " id ", null));
		return modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> allCategory() {
		List<Category> allCat = catRepo.findAll();
		CategoryDto mapCat = modelmapper.map(allCat, CategoryDto.class);
		List<CategoryDto> findCatDto = allCat.stream().map(cat -> mapCat).collect(Collectors.toList());
		return findCatDto;
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category category = catRepo.findById(catId)
				.orElseThrow(() -> new ResouseNotFoundException("category not found : ", " id ", null));
		catRepo.delete(category);

	}

}
