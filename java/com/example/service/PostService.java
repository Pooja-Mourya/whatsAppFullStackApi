package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer catId);
	
	PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer catId);
	
	PostDto postById(Integer postId);
	
	Page<PostDto> allPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	void deletePost(Integer postId);
	
	List<PostDto> listByCategoryId(Integer catId);
	
	List<PostDto> listByUserId(Integer userId);
	
	List<PostDto> postSerchByKey(String keyString);
}
