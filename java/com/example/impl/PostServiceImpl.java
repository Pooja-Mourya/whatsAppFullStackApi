package com.example.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exceptionHandler.ResouseNotFoundException;
import com.example.payload.CategoryDto;
import com.example.payload.PostDto;
import com.example.repository.CategoryRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import com.example.response.ApiResponse;
import com.example.service.PostService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CategoryRepository catRepo;

//	@Override
//	public List<PostDto> allPost(Integer pageNumber, Integer pageSize){
//		List<Post> findAll = postRepo.findAll();
//		List<PostDto> list = findAll.stream().map(li-> modelMapper.map(li, PostDto.class)).collect(Collectors.toList());
//		return list;
//	}

	@Override
	public Page<PostDto> allPost(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sortBy).descending());
		Page<Post> postPage = postRepo.findAll(pageable);

		return postPage.map(post -> modelMapper.map(post, PostDto.class));
	}

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
		User user = userRepo.findById(userId).get();
		Category category = catRepo.findById(catId).get();
		Post map = modelMapper.map(postDto, Post.class);
		map.setContent(postDto.getContent());
		map.setPostImage(postDto.getPostImage());
		map.setTitle(postDto.getTitle());
		map.setUser(user);
		map.setCategory(category);
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		map.setCreatedAt(date);
		Post save = postRepo.save(map);
		PostDto dto = modelMapper.map(save, PostDto.class);
		return dto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer catId) {
		User user = userRepo.findById(userId).get();
		Category category = catRepo.findById(catId).get();

		postRepo.findById(postId).orElseThrow(() -> new ResouseNotFoundException("Post", "id", null));
		Post map = modelMapper.map(postDto, Post.class);
		map.setContent(postDto.getContent());
		map.setCreatedAt(postDto.getCreatedAt());
		map.setPostImage(postDto.getPostImage());
		map.setTitle(postDto.getTitle());
		map.setUser(user);
		map.setCategory(category);
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		map.setCreatedAt(date);
		Post save = postRepo.save(map);
		PostDto saveMap = modelMapper.map(save, PostDto.class);
		return saveMap;
	}

	@Override
	public PostDto postById(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResouseNotFoundException("Category", "id", null));
		PostDto map = modelMapper.map(post, PostDto.class);
		return map;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResouseNotFoundException("Post", "id", null));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> listByCategoryId(Integer catId) {
		Category category = catRepo.findById(catId)
				.orElseThrow(() -> new ResouseNotFoundException("Category", "id", null));
		List<Post> findByCategoryList = postRepo.findByCategory(category);
		List<PostDto> map = findByCategoryList.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return map;
	}

	@Override
	public List<PostDto> listByUserId(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResouseNotFoundException("User", "id", null));
		List<Post> listByUser = postRepo.findByUser(user);
		List<PostDto> postByUser = listByUser.stream().map(list -> modelMapper.map(list, PostDto.class))
				.collect(Collectors.toList());
		return postByUser;
	}

	@Override
	public List<PostDto> postSerchByKey(String keyString) {
		Page<Post> byTitleContaining = postRepo.findByTitleContaining(keyString, null);
		List<PostDto> lists = byTitleContaining.stream().map(title->modelMapper.map(title, PostDto.class)).collect(Collectors.toList());
		return lists;
	}


}
